package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusQueueServer")
@Qualifier("eventBusQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("eventBusQueue")
	private StanderCharacteristicShell<Queue<Event>> eventQueue;
	
	@Autowired
	@Qualifier("eventBusQueue")
	private QueueControl<Event> eventQueueControl;
	
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
     	ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener","bootInitializationServerListener");
		Optional<Queue<Event>> queueArg = BeanContainer.instance().getBean(Optional.of("eventBusQueueCharacteristicInitialize"), Optional.class);
		eventQueue.initialize(queueArg);
		eventQueueControl.start();
	}

	@Override
	public void shutDown() {
		eventQueueControl.shutdown();
	}

}
