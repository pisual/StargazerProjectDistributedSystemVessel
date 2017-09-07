package com.stargazerproject.queue.impl.resources.shell;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("eventDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventDisruptorShell extends BaseQueueRingBuffer<Event, EventQueueEvent> implements BaseCharacteristic<Queue<Event>>{
	
	@Autowired
	@Qualifier("eventFactory")
	private EventFactory<EventQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("eventQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	private EventDisruptorShell() {
		super.translator = new EventTranslatorOneArg<EventQueueEvent, Event>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, Event event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	@Override
	@Bean(name="eventQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<Queue<Event>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Order_Size_of_bufferSize")).get());
		disruptor = new Disruptor<EventQueueEvent>(eventFactory, bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(handler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Order_Number_of_consumers")).get());
		handler = new EventHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("eventHandler"), WorkHandler.class);
		}
	}
	
}