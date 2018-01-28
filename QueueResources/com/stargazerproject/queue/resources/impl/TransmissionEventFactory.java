package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.TransmissionEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component(value="transmissionEventFactory")
@Qualifier("transmissionEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransmissionEventFactory extends QueueEventFactory<TransmissionEvent>{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionEventFactory() {
		super();
	}
	
	@Override
	public TransmissionEvent newInstance() {
		return new TransmissionEvent();
	}
	
}
