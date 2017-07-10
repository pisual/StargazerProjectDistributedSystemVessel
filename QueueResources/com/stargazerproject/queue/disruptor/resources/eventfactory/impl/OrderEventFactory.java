package com.stargazerproject.queue.disruptor.resources.eventfactory.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.queue.disruptor.resources.eventfactory.base.impl.QueueEventFactory;

@Component
@Qualifier("orderEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderEventFactory extends QueueEventFactory<Order>{
	
	/** @construction 初始化构造 **/
	private OrderEventFactory() {
		super();
		}

	@Override
	public Order newInstance() {
		return new Order();
	}
	
}
