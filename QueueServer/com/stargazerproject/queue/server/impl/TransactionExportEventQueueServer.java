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
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionExportEventQueueServer")
@Qualifier("transactionExportEventQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExportEventQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("transactionExportDisruptorShell")
	private BaseCharacteristic<Queue<Transaction>> transactionExportDisruptorShell;
	
	@Autowired
	@Qualifier("transactionExportQueue")
	private StanderCharacteristicShell<Queue<Transaction>> transactionExportQueue;
	
	@Autowired
	@Qualifier("transactionExportQueue")
	private QueueControl<Transaction> transactionExportQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransactionExportEventQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportEventQueueServer(Optional<BaseCharacteristic<Queue<Transaction>>> transactionExportDisruptorShellArg, 
			                                 Optional<StanderCharacteristicShell<Queue<Transaction>>> transactionExportQueueArg, 
			                                 Optional<QueueControl<Transaction>> transactionExportQueueControlArg) {
		transactionExportDisruptorShell = transactionExportDisruptorShellArg.get();
		transactionExportQueue = transactionExportQueueArg.get();
		transactionExportQueueControl = transactionExportQueueControlArg.get();
	}
	
	@Override
	public void startUp() {
		transactionExportQueue.initialize(transactionExportDisruptorShell.characteristic());
		transactionExportQueueControl.start();
	}

	@Override
	public void shutDown() {
		transactionExportQueueControl.shutdown();
	}

}
