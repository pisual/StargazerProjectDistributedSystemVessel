package com.stargazerproject.messagequeue.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.order.impl.Order;

@Component(value="orderMessageQueueControl")
@Qualifier("orderMessageQueueControl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueControl implements MessageQueueControl<Order>{

	@Override
	public void join(Optional<String> messageQueueUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}


}
