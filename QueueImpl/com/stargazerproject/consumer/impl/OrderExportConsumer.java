package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.QueueConsumer;

@Component(value="orderExportConsumer")
@Qualifier("orderExportConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderExportConsumer implements QueueConsumer<Order>{

	@Autowired
	@Qualifier("eventExecute")
	private EventAnalysis execute;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	protected OrderExportConsumer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderExportConsumer(Optional<EventAnalysis> executeArg) {
		execute = executeArg.get();
	}
	
	@Override
	public void consumer(Optional<Order> e) {
		System.out.println("Order 已经放置到输出队列 " + e.get().toString());
	}

}
