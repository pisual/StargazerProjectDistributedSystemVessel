package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.impl.OrderQueue;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("orderQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderQueueServer extends OrderQueue implements StanderServiceShell{

	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<Queue<Order>> queueArg = BeanContainer.instance().getBean(Optional.of("orderDisruptorQueueCharacteristic"), Optional.class);
		initialize(queueArg);
	}

	@Override
	public void shutDown() {
	}

}
