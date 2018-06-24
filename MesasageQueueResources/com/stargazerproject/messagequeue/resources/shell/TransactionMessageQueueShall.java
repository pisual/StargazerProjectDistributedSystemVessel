package com.stargazerproject.messagequeue.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.transaction.impl.Order;

@Component(value="orderMessageQueueShall")
@Qualifier("orderMessageQueueShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueShall implements MessageQueue<Order>, BaseCharacteristic<MessageQueue<Order>>{

	@Autowired
	@Qualifier("orderMessageQueueControlCharacteristic")
	private BaseCharacteristic<MessageQueueControl<Order>> orderMessageQueueControlCharacteristic;
	
	@Autowired
	@Qualifier("orderMessageQueueAcquireCharacteristic")
	private BaseCharacteristic<MessageQueueAcquire<Order>> orderMessageQueueAcquireCharacteristic;
	
	@Autowired
	@Qualifier("orderMessageQueuePushCharacteristic")
	private BaseCharacteristic<MessageQueuePush<Order>> orderMessageQueuePushCharacteristic;
	
	private MessageQueueControl<Order> orderMessageQueueControl;
	
	private MessageQueueAcquire<Order> messageQueueAcquire;
	
	private MessageQueuePush<Order> messageQueuePush;
	
	@Override
	public Optional<MessageQueue<Order>> characteristic() {
		return Optional.of(this);
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
	public void join() {
		orderMessageQueueControl.join();
	}

	@Override
	public void out() {
		orderMessageQueueControl.out();
	}

}
