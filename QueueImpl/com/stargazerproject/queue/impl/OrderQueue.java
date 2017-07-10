package com.stargazerproject.queue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;

/** 
 *  @name Order队列
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class OrderQueue extends StandQueue<Order> implements StanderCharacteristicShell<Queue<Order>>{

	/** @construction 初始化构造 **/
	protected OrderQueue() {}

	@Override
	public void initialize(Optional<Queue<Order>> queueArg) {
		queue = queueArg.get();
	}

}