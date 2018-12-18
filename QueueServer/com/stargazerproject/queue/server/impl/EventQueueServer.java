package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.transaction.Event;

@Component(value="eventQueueServer")
@Qualifier("eventQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("eventDisruptorShell")
	private BaseCharacteristic<Queue<Event>> eventDisruptorShell;
	
	@Autowired
	@Qualifier("eventQueue")
	private StanderCharacteristicShell<Queue<Event>> eventQueue;
	
	@Autowired
	@Qualifier("eventQueue")
	private QueueControl<Event> eventQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventQueueServer(Optional<BaseCharacteristic<Queue<Event>>> eventDisruptorShellArg, Optional<StanderCharacteristicShell<Queue<Event>>> eventQueueArg, Optional<QueueControl<Event>> eventQueueControlArg) {
		eventDisruptorShell = eventDisruptorShellArg.get();
		eventQueueControl = eventQueueControlArg.get();
		eventQueue = eventQueueArg.get();
	}
	
	@Override
	public void startUp() {
		eventQueue.initialize(eventDisruptorShell.characteristic());
		eventQueueControl.start();
	}

	@Override
	public void shutDown() {
		eventQueueControl.shutdown();
	}

}
