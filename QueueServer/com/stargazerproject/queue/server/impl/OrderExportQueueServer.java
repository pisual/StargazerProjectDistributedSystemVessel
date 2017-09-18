package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("orderExportQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("orderExportQueue")
	private StanderCharacteristicShell<Queue<Order>> eventQueue;
	
	@Autowired
	@Qualifier("orderExportQueue")
	private QueueControl<Order> eventQueueControl;
	
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
     	ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<Queue<Order>> queueArg = BeanContainer.instance().getBean(Optional.of("orderExportQueueCharacteristicInitialize"), Optional.class);
		eventQueue.initialize(queueArg);
		eventQueueControl.start();
	}

	@Override
	public void shutDown() {
		eventQueueControl.shutdown();
	}

}
