package com.stargazerproject.messagequeue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.base.impl.BaseMessageQueue;
import com.stargazerproject.order.impl.Order;

@Component(value="orderMessageQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("orderMessageQueue")
public class OrderMessageQueue extends BaseMessageQueue<Order> implements StanderCharacteristicShell<MessageQueue<Order>>{
	
	protected OrderMessageQueue() {}

	@Override
	public void initialize(Optional<MessageQueue<Order>> messageQueueArg) {
		messageQueue = messageQueueArg.get();
	}
	
}
