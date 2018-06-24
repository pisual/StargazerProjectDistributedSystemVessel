package com.stargazerproject.queue.resources.shell;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.PhasedBackoffWaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.TransactionExportEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.TransactionExportEventHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionExportEventDisruptorShell")
@Qualifier("transactionExportEventDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExportEventDisruptorShell extends BaseQueueRingBuffer<Transaction, TransactionExportEvent> implements BaseCharacteristic<Queue<Transaction>>{
	
	/** @name TransactionExport队列的缓存数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionExportEventQueue_Memory_BufferSize;
	
	/** @name TransactionExport队列的消费者数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumers;
	
	@Autowired
	@Qualifier("transactionExportEventFactory")
	private EventFactory<TransactionExportEvent> transactionExportEventFactory;
	
	@Autowired
	@Qualifier("transactionExportEventThreadFactory")
	private ThreadFactory transactionExportEventThreadFactory;
	
	@Autowired
	@Qualifier("cleanTransactionExportEventHandler")
	private WorkHandler<TransactionExportEvent> cleanTransactionExportEventHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransactionExportEventDisruptorShell() {
		super.translator = new EventTranslatorOneArg<TransactionExportEvent, Transaction>() {
			public void translateTo(TransactionExportEvent transactionExportEvent, long sequence, Transaction transaction) {
				transactionExportEvent.setTransaction(transaction);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportEventDisruptorShell(Optional<String> Kernel_Queue_TransactionExportEventQueue_Memory_BufferSizeArg,
	                                            Optional<String> Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumersArg,
			                                    Optional<EventFactory<TransactionExportEvent>> transactionExportEventFactoryArg, 
			                                    Optional<ThreadFactory> transactionExportEventThreadFactoryArg, 
			                                    Optional<WorkHandler<TransactionExportEvent>> cleanTransactionExportEventHandlerArg) {
		Kernel_Queue_TransactionExportEventQueue_Memory_BufferSize = Kernel_Queue_TransactionExportEventQueue_Memory_BufferSizeArg.get();
		Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumers = Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumersArg.get();
		transactionExportEventFactory =transactionExportEventFactoryArg.get();
		transactionExportEventThreadFactory = transactionExportEventThreadFactoryArg.get();
		cleanTransactionExportEventHandler = cleanTransactionExportEventHandlerArg.get();
		
		super.translator = new EventTranslatorOneArg<TransactionExportEvent, Transaction>() {
			public void translateTo(TransactionExportEvent transactionExportEvent, long sequence, Transaction transaction) {
				transactionExportEvent.setTransaction(transaction);
			}
		};
	}
	
	@Override
	public Optional<Queue<Transaction>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		disruptor = new Disruptor<TransactionExportEvent>(transactionExportEventFactory, 
				                                          getIntegerParameter(Kernel_Queue_TransactionExportEventQueue_Memory_BufferSize), 
				                                          transactionExportEventThreadFactory, 
				                                          ProducerType.SINGLE, 
				                                          new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanTransactionExportEventHandler);
	}
	
	private void handleEvents(){
		handler = new TransactionExportEventHandler[getIntegerParameter(Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumers)];
		for(int i=0; i<getIntegerParameter(Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("transactionExportEventHandler"), com.lmax.disruptor.WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
}