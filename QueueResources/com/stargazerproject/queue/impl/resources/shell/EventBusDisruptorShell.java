package com.stargazerproject.queue.impl.resources.shell;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.EventBusHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusDisruptorShell")
@Qualifier("eventBusDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusDisruptorShell extends BaseQueueRingBuffer<Event, EventQueueEvent> implements BaseCharacteristic<Queue<Event>>{
	
	@Autowired
	@Qualifier("eventFactory")
	private EventFactory<EventQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("eventQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	@Autowired
	@Qualifier("cleanEventHandler")
	private WorkHandler<EventQueueEvent> cleanEventHandler;
	
	private EventBusDisruptorShell() {
		super.translator = new EventTranslatorOneArg<EventQueueEvent, Event>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, Event event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	@Override
	@Bean(name="eventBusQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<Queue<Event>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Event_Bus_Size_of_bufferSize")).get());
		disruptor = new Disruptor<EventQueueEvent>(eventFactory, bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
	//	disruptor.setDefaultExceptionHandler(new EventOutTimeExceptionHandler<EventQueueEvent>());
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanEventHandler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Event_Bus_Number_of_consumers")).get());
		handler = new EventBusHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("eventBusHandler"), com.lmax.disruptor.WorkHandler.class);
		}
	}
	
}