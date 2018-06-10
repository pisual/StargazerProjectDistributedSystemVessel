package com.stargazerproject.queue.resources.shell;

import java.util.concurrent.Executors;
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
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.base.impl.BaseEvent;

@Component(value="eventDisruptorShell")
@Qualifier("eventDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventDisruptorShell extends BaseQueueRingBuffer<BaseEvent, EventQueueEvent> implements BaseCharacteristic<Queue<BaseEvent>>{
	
	/** @name 接收Event队列的缓存数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_ReceiveEventQueue_Memory_BufferSize;
	
	/** @name 接收Event队列的消费者数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_ReceiveEventQueue_Consumer_NumberOfConsumers;
	
	@Autowired
	@Qualifier("eventFactory")
	private EventFactory<EventQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("eventQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("eventResultMergeHandler")
	private WorkHandler<EventQueueEvent> eventResultMergeHandler;
	
	@Autowired
	@Qualifier("cleanEventHandler")
	private WorkHandler<EventQueueEvent> cleanEventHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventDisruptorShell() {
		super.translator = new EventTranslatorOneArg<EventQueueEvent, BaseEvent>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, BaseEvent event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventDisruptorShell(Optional<ThreadFactory> threadFactoryArg, Optional<EventFactory<EventQueueEvent>> eventFactoryArg, Optional<WorkHandler<EventQueueEvent>> cleanEventHandlerArg) {
		eventFactory = eventFactoryArg.get();
		threadFactory = threadFactoryArg.get();
		cleanEventHandler = cleanEventHandlerArg.get();
		
		super.translator = new EventTranslatorOneArg<EventQueueEvent, BaseEvent>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, BaseEvent event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	@Override
	public Optional<Queue<BaseEvent>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		disruptor = new Disruptor<EventQueueEvent>(eventFactory, getIntegerParameter(Kernel_Queue_ReceiveEventQueue_Memory_BufferSize), Executors.defaultThreadFactory(), ProducerType.SINGLE, new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
	//	disruptor.setDefaultExceptionHandler(new EventOutTimeExceptionHandler<EventQueueEvent>());
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(eventResultMergeHandler).thenHandleEventsWithWorkerPool(cleanEventHandler);
	}
	
	private void handleEvents(){
		handler = new EventHandler[getIntegerParameter(Kernel_Queue_ReceiveEventQueue_Consumer_NumberOfConsumers)];
		for(int i=0; i<getIntegerParameter(Kernel_Queue_ReceiveEventQueue_Consumer_NumberOfConsumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("eventHandler"), com.lmax.disruptor.WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
}