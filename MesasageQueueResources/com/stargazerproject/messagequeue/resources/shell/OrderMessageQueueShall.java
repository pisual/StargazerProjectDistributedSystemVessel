package com.stargazerproject.messagequeue.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="orderMessageQueueShall")
@Qualifier("orderMessageQueueShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueShall implements MessageQueue<Order>, BaseCharacteristic<MessageQueue<Order>>{

	private Optional<MessageQueueControl<Order>> orderMessageQueueControl;
	private Optional<MessageQueueAcquire<Order>> messageQueueAcquire;
	private Optional<MessageQueuePush<Order>> messageQueuePush;
	
	@Override
	@Bean(name="orderMessageQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<MessageQueue<Order>> characteristic() {
		orderMessageQueueControl= BeanContainer.instance().getBean(Optional.of("orderMessageQueueControlCharacteristic"),Optional.class);
		messageQueueAcquire= BeanContainer.instance().getBean(Optional.of("orderMessageQueueAcquireCharacteristic"),Optional.class);
		messageQueuePush= BeanContainer.instance().getBean(Optional.of("orderMessageQueuePushCharacteristic"),Optional.class);
		return Optional.of(this);
	}

	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		return messageQueueAcquire.get().get(messageNumber);
	}
	
	@Override
	public void put(Optional<List<Order>> t) {
		messageQueuePush.get().put(t);
	}

	@Override
	public void join() {
		orderMessageQueueControl.get().join();
	}

	@Override
	public void out() {
		orderMessageQueueControl.get().out();
	}

}
