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
import com.stargazerproject.transaction.impl.Order;

@Component(value="orderExportQueueServer")
@Qualifier("orderExportQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("orderExportDisruptorShell")
	private BaseCharacteristic<Queue<Order>> orderExportDisruptorShell;
	
	@Autowired
	@Qualifier("orderExportQueue")
	private StanderCharacteristicShell<Queue<Order>> eventQueue;
	
	@Autowired
	@Qualifier("orderExportQueue")
	private QueueControl<Order> eventQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderExportQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderExportQueueServer(Optional<BaseCharacteristic<Queue<Order>>> orderExportDisruptorShellArg, Optional<StanderCharacteristicShell<Queue<Order>>> eventQueueArg, Optional<QueueControl<Order>> eventQueueControlArg) {
		orderExportDisruptorShell = orderExportDisruptorShellArg.get();
		eventQueueControl = eventQueueControlArg.get();
		eventQueue = eventQueueArg.get();
	}
	
	@Override
	public void startUp() {
		eventQueue.initialize(orderExportDisruptorShell.characteristic());
		eventQueueControl.start();
	}

	@Override
	public void shutDown() {
		eventQueueControl.shutdown();
	}

}
