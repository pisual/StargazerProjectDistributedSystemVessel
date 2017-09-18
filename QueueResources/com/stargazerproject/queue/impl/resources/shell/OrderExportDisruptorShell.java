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
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.OrderExportEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.OrderExportHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("orderExportDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportDisruptorShell extends BaseQueueRingBuffer<Order, OrderExportEvent> implements BaseCharacteristic<Queue<Order>>{
	
	@Autowired
	@Qualifier("orderExportEventFactory")
	private EventFactory<OrderExportEvent> orderExportEventFactory;
	
	@Autowired
	@Qualifier("orderExportThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	
	@Autowired
	@Qualifier("cleanOrderExportHandler")
	private WorkHandler<OrderExportEvent> cleanOrderExportHandler;
	
	private OrderExportDisruptorShell() {
		super.translator = new EventTranslatorOneArg<OrderExportEvent, Order>() {
			public void translateTo(OrderExportEvent orderExportEvent, long sequence, Order order) {
				orderExportEvent.setOrder(order);
			}
		};
	}
	
	@Override
	@Bean(name="orderExportQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<Queue<Order>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Event_Size_of_bufferSize")).get());
		disruptor = new Disruptor<OrderExportEvent>(orderExportEventFactory, bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanOrderExportHandler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Event_Number_of_consumers")).get());
		handler = new OrderExportHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("orderExportHandler"), com.lmax.disruptor.WorkHandler.class);
		}
	}
	
}