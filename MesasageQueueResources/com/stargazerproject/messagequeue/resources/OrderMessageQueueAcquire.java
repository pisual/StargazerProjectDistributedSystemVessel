package com.stargazerproject.messagequeue.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.order.impl.Order;

@EnableBinding(OrderAcquireMessageQueueChannel.class)
@Component(value="orderMessageQueueAcquire")
@Qualifier("orderMessageQueueAcquire")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueAcquire implements MessageQueueAcquire<Order>{
	
	@Autowired
	@Qualifier("OrderAcquireMessageQueue")
	private PollableChannel pollableChannel;

	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		List<Order> orderList = new ArrayList<Order>();
		Message<?> inbound = pollableChannel.receive(100);
		Order order = (Order) inbound.getPayload();
		orderList.add(order);
		return Optional.of(orderList);
	}
}
