package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.resources.QueuethreadFactory;

@Component(value="transmissionQueueThreadFactory")
@Qualifier("transmissionQueueThreadFactory")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransmissionQueueThreadFactory extends QueuethreadFactory{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionQueueThreadFactory() {
		super();
	}
	
}
