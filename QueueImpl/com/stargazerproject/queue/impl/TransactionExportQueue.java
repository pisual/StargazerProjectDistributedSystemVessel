package com.stargazerproject.queue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name Transaction归并输出队列
 *  @illustrate 针对Transaction中的Event调用结果完成度分析器来进行分析归位
 *  
 *  Event1---->(Check Complete)
 *  Event2---->(Check Complete)
 *                               If Complete  ->>>> TransactionExportQueue()
 *  Event3---->(Check Complete)
 *  Event4---->(Check Complete)
 *  
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="transactionExportQueue")
@Qualifier("transactionExportQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExportQueue extends StandQueue<Transaction> implements StanderCharacteristicShell<Queue<Transaction>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected TransactionExportQueue() {}

	@Override
	public void initialize(Optional<Queue<Transaction>> queueArg) {
		queue = queueArg.get();
	}

}