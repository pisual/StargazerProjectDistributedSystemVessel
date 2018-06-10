package com.stargazerproject.queue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;
import com.stargazerproject.transaction.impl.Order;

/** 
 *  @name Order归并输出队列
 *  @illustrate Order归并输出队列的实现
 *  
 *  
 *  Event1---->(Check Complete)
 *  Event2---->(Check Complete)
 *                               If Complete  ->>>> OrderExportQueue()
 *  Event3---->(Check Complete)
 *  Event4---->(Check Complete)
 *  
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="orderExportQueue")
@Qualifier("OrderExportQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderExportQueue extends StandQueue<Order> implements StanderCharacteristicShell<Queue<Order>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected OrderExportQueue() {}

	@Override
	public void initialize(Optional<Queue<Order>> queueArg) {
		queue = queueArg.get();
	}

}