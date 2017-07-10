package com.stargazerproject.queue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;

/** 
 *  @name Log队列
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class LogQueue extends StandQueue<LogData> implements StanderCharacteristicShell<Queue<LogData>>{

	/** @construction 初始化构造 **/
	protected LogQueue() {}

	@Override
	public void initialize(Optional<Queue<LogData>> queueArg) {
		queue = queueArg.get();
	}

}