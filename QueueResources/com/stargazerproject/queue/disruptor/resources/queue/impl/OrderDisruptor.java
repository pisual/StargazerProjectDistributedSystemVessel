package com.stargazerproject.queue.disruptor.resources.queue.impl;

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
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.disruptor.resources.queue.BaseQueueRingBuffer;
import com.stargazerproject.queue.handler.impl.OrderHandler;

@Component
@Qualifier("orderDisruptor")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderDisruptor extends BaseQueueRingBuffer<Order> implements BaseCharacteristic<Queue<Order>>{
	
	@Autowired
	@Qualifier("orderEventFactory")
	private EventFactory<Order> eventFactory;
	
	@Autowired
	@Qualifier("orderQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	private OrderDisruptor() {}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Order_Size_of_bufferSize")).get());
		disruptor = new Disruptor<Order>(eventFactory, bufferSize, threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(handler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Order_Number_of_consumers")).get());
		handler = new OrderHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = new OrderHandler();
		}
	}

	@Override
	@Bean(name="orderDisruptorQueueCharacteristic")
	@Lazy(true)
	public Optional<Queue<Order>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
}