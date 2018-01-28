package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

@Component(value="transmissionQueueServer")
@Qualifier("transmissionQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransmissionQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("transmissionDisruptorShell")
	private BaseCharacteristic<Queue<Transmission>> transmissionDisruptorShell;
	
	@Autowired
	@Qualifier("transmissionQueue")
	private StanderCharacteristicShell<Queue<Transmission>> transmissionQueue;
	
	@Autowired
	@Qualifier("transmissionQueue")
	private QueueControl<Transmission> transmissionQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransmissionQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionQueueServer(Optional<BaseCharacteristic<Queue<Transmission>>> transmissionDisruptorShellArg, Optional<StanderCharacteristicShell<Queue<Transmission>>> transmissionQueueArg, Optional<QueueControl<Transmission>> transmissionQueueControlArg) {
		transmissionDisruptorShell = transmissionDisruptorShellArg.get();
		transmissionQueueControl = transmissionQueueControlArg.get();
		transmissionQueue = transmissionQueueArg.get();
	}
	
	@Override
	public void startUp() {
		transmissionQueue.initialize(transmissionDisruptorShell.characteristic());
		transmissionQueueControl.start();
	}

	@Override
	public void shutDown() {
		transmissionQueueControl.shutdown();
	}

}
