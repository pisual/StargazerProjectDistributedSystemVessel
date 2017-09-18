package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.OrderExportEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component
@Qualifier("orderExportEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportEventFactory extends QueueEventFactory<OrderExportEvent>{
	@Override
	public OrderExportEvent newInstance() {
		return new OrderExportEvent();
	}
	
}
