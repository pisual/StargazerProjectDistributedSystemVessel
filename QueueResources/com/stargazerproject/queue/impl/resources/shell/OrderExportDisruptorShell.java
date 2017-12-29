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
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.OrderExportEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.OrderExportHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="orderExportDisruptorShell")
@Qualifier("orderExportDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportDisruptorShell extends BaseQueueRingBuffer<Order, OrderExportEvent> implements BaseCharacteristic<Queue<Order>>{
	
	/** @name Order Export队列的缓存数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Order_Export_Size_of_bufferSize;
	
	/** @name Order Export队列的消费者数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Order_Export_Number_of_consumers;
	
	@Autowired
	@Qualifier("orderExportEventFactory")
	private EventFactory<OrderExportEvent> orderExportEventFactory;
	
	@Autowired
	@Qualifier("orderExportThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("cleanOrderExportHandler")
	private WorkHandler<OrderExportEvent> cleanOrderExportHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderExportDisruptorShell() {
		super.translator = new EventTranslatorOneArg<OrderExportEvent, Order>() {
			public void translateTo(OrderExportEvent orderExportEvent, long sequence, Order order) {
				orderExportEvent.setOrder(order);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderExportDisruptorShell(Optional<EventFactory<OrderExportEvent>> orderExportEventFactoryArg, Optional<ThreadFactory> threadFactoryArg, Optional<WorkHandler<OrderExportEvent>> cleanOrderExportHandlerArg) {
		orderExportEventFactory =orderExportEventFactoryArg.get();
		threadFactory = threadFactoryArg.get();
		cleanOrderExportHandler = cleanOrderExportHandlerArg.get();
		
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
		disruptor = new Disruptor<OrderExportEvent>(orderExportEventFactory, getIntegerParameter(Order_Export_Size_of_bufferSize), Executors.defaultThreadFactory(), ProducerType.SINGLE, new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanOrderExportHandler);
	}
	
	private void handleEvents(){
		handler = new OrderExportHandler[getIntegerParameter(Order_Export_Number_of_consumers)];
		for(int i=0; i<getIntegerParameter(Order_Export_Number_of_consumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("orderExportHandler"), com.lmax.disruptor.WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
}