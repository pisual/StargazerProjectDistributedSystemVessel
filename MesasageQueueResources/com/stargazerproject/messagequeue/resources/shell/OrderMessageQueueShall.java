package com.stargazerproject.messagequeue.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;

public class OrderMessageQueueShall implements MessageQueue<Order>, BaseCharacteristic<MessageQueue<Order>>{

	@Autowired
	@Qualifier("orderMessageQueueAcquire")
	private MessageQueueAcquire<Order> messageQueueAcquire;
	
	@Autowired
	@Qualifier("orderMessageQueuePush")
	private MessageQueuePush<Order> messageQueuePush;
	
	@Override
	@Bean(name="orderMessageQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<MessageQueue<Order>> characteristic() {
		return null;
	}

	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		return messageQueueAcquire.get(messageNumber);
	}
	
	@Override
	public void put(Optional<List<Order>> t) {
		messageQueuePush.put(t);
	}

	@Override
	public void join(Optional<String> messageQueueUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}

}