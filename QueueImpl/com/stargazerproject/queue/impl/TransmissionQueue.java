package com.stargazerproject.queue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;

/** 
 *  @name Transmission队列
 *  @illustrate Transmission队列,低速低占用类的队列，低速队列用于对于速度不敏感，并需要长时间低占用率的事件
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="transmissionQueue")
@Qualifier("transmissionQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransmissionQueue extends StandQueue<Transmission> implements StanderCharacteristicShell<Queue<Transmission>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected TransmissionQueue() {}

	@Override
	public void initialize(Optional<Queue<Transmission>> queueArg) {
		queue = queueArg.get();
	}

}