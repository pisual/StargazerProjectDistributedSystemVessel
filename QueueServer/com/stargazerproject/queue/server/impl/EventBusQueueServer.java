package com.stargazerproject.queue.server.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

@Component(value="eventBusQueueServer")
@Qualifier("eventBusQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("eventBusDisruptorShell")
	private BaseCharacteristic<Queue<Event>> eventBusDisruptorShell;
	
	@Autowired
	@Qualifier("eventBusQueue")
	private StanderCharacteristicShell<Queue<Event>> eventQueue;
	
	@Autowired
	@Qualifier("eventBusQueue")
	private QueueControl<Event> eventQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventBusQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventBusQueueServer(Optional<BaseCharacteristic<Queue<Event>>> eventBusDisruptorShellArg, Optional<StanderCharacteristicShell<Queue<Event>>> eventQueueArg, Optional<QueueControl<Event>> eventQueueControlArg){
		eventBusDisruptorShell = eventBusDisruptorShellArg.get();
		eventQueueControl = eventQueueControlArg.get();
		eventQueue = eventQueueArg.get();
	}
	
	@Override
	public void startUp() {
		eventQueue.initialize(eventBusDisruptorShell.characteristic());
		eventQueueControl.start();
	}

	@Override
	public void shutDown() {
		eventQueueControl.shutdown();
	}

}
