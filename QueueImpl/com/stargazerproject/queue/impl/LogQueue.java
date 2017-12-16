package com.stargazerproject.queue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Component(value="logQueue")
@Qualifier("logQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogQueue extends StandQueue<LogData> implements StanderCharacteristicShell<Queue<LogData>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected LogQueue() {}

	@Override
	public void initialize(Optional<Queue<LogData>> queueArg) {
		queue = queueArg.get();
	}

}