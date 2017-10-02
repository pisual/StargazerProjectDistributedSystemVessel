package com.stargazerproject.messagequeue.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;

@EnableBinding(OrderPushMessageQueueChannel.class)
@Component(value="orderMessageQueuePush")
@Qualifier("orderMessageQueuePush")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueuePush implements MessageQueuePush<Order>{

    @Autowired
    private OrderPushMessageQueueChannel pushChannel;
	
	@Override
	public void put(Optional<List<Order>> t) {
		pushChannel.output().send(MessageBuilder.withPayload(t).build());
	}

}
