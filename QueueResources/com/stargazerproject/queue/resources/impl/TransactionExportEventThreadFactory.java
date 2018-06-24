package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.resources.QueuethreadFactory;

@Component(value="transactionExportEventThreadFactory")
@Qualifier("transactionExportEventThreadFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExportEventThreadFactory extends QueuethreadFactory{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportEventThreadFactory() {
		super();
	}
	
}
