package com.stargazerproject.messagequeue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.base.impl.BaseMessageQueue;
import com.stargazerproject.model.order.impl.Order;

public class OrderMessageQueue extends BaseMessageQueue<Order> implements StanderCharacteristicShell<MessageQueue<Order>>{
	
	protected OrderMessageQueue() {}

	@Override
	public void initialize(Optional<MessageQueue<Order>> messageQueueArg) {
		messageQueue = messageQueueArg.get();
	}
	
}
