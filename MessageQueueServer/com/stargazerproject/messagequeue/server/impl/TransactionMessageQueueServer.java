package com.stargazerproject.messagequeue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name TransactionMessageQueue服务的实现
 *  @illustrate 继承于ServiceShell的TransactionMessageQueue相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="transactionMessageQueueServer")
@Qualifier("transactionMessageQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionMessageQueueServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("transactionMessageQueue")
	private StanderCharacteristicShell<MessageQueue<Transaction>> transactionMessageQueue;
	
	@Autowired
	@Qualifier("transactionMessageQueueShall")
	private BaseCharacteristic<MessageQueue<Transaction>> transactionMessageQueueShall;
	
	@Autowired
	@Qualifier("transactionMessageQueueControlCharacteristic")
	private BaseCharacteristic<MessageQueueControl<Transaction>> transactionMessageQueueControlCharacteristic;
	
	private MessageQueueControl<Transaction> transactionMessageQueueControl;
	
	/** @construction 初始化构造 **/
	private TransactionMessageQueueServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		transactionMessageQueue.initialize(transactionMessageQueueShall.characteristic());

	//	transactionMessageQueueControl.join();
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	//	transactionMessageQueueControl = transactionMessageQueueControlCharacteristic.characteristic().get();
	//	transactionMessageQueueControl.out();;
	}
	
}