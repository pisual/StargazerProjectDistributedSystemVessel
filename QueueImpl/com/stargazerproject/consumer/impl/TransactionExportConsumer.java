package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.queue.QueueConsumer;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionExportConsumer")
@Qualifier("transactionExportConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionExportConsumer implements QueueConsumer<Transaction>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportConsumer() {}
	
	@Override
	public void consumer(Optional<Transaction> transaction) {
		System.out.println("Transaction已经放置到输出队列 " + transaction.get().toString());
	}

}
