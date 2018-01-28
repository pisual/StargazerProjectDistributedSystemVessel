package com.stargazerproject.queue.resources.shell;

import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.TransmissionEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.LowOccupancyWaitStrategy;
import com.stargazerproject.queue.resources.impl.TransmissionQueueHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name TransmissionQueue
 *  @Shell 
 *  @illustrate 
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component(value="transmissionDisruptorShell")
@Qualifier("transmissionDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransmissionDisruptorShell extends BaseQueueRingBuffer<Transmission, TransmissionEvent> implements BaseCharacteristic<Queue<Transmission>>{
	
	/** @name 接收Transmission队列的缓存数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransmissionQueue_Memory_BufferSize;
	
	/** @name 接收Transmission队列的消费者数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransmissionQueue_Consumer_NumberOfConsumers;
	
	/** @name 接收Transmission队列的低速队列的寻轮时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransmissionQueue_Strategy_SearchIntervalTime;
	
	@Autowired
	@Qualifier("transmissionEventFactory")
	private EventFactory<TransmissionEvent> transmissionEventFactory;
	
	@Autowired
	@Qualifier("transmissionQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("cleanTransmissionEventHandler")
	private WorkHandler<TransmissionEvent> cleanTransmissionEventHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransmissionDisruptorShell() {
		super.translator = new EventTranslatorOneArg<TransmissionEvent, Transmission>() {
			public void translateTo(TransmissionEvent transmissionEvent, long sequence, Transmission transmission) {
				transmissionEvent.setTransmission(transmission);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionDisruptorShell(Optional<ThreadFactory> threadFactoryArg, Optional<EventFactory<TransmissionEvent>> transmissionEventFactoryArg, Optional<WorkHandler<TransmissionEvent>> cleanTransmissionEventHandlerArg) {
		transmissionEventFactory = transmissionEventFactoryArg.get();
		threadFactory = threadFactoryArg.get();
		cleanTransmissionEventHandler = cleanTransmissionEventHandlerArg.get();
		
		super.translator = new EventTranslatorOneArg<TransmissionEvent, Transmission>() {
			public void translateTo(TransmissionEvent transmissionEvent, long sequence, Transmission transmission) {
				transmissionEvent.setTransmission(transmission);
			}
		};
	}
	
	@Override
	public Optional<Queue<Transmission>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		disruptor = new Disruptor<TransmissionEvent>(transmissionEventFactory, getIntegerParameter(Kernel_Queue_TransmissionQueue_Memory_BufferSize), threadFactory, ProducerType.SINGLE, new LowOccupancyWaitStrategy(200, getLongParameter(Kernel_Queue_TransmissionQueue_Strategy_SearchIntervalTime)));
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanTransmissionEventHandler);
	}
	
	private void handleEvents(){
		handler = new TransmissionQueueHandler[getIntegerParameter(Kernel_Queue_TransmissionQueue_Consumer_NumberOfConsumers)];
		for(int i=0; i<getIntegerParameter(Kernel_Queue_TransmissionQueue_Consumer_NumberOfConsumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("transmissionQueueHandler"), WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
	private Long getLongParameter(String value){
		return Long.parseLong(value);
	}
	
}