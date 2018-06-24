package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.TransactionExportEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component(value="transactionExportEventFactory")
@Qualifier("transactionExportEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExportEventFactory extends QueueEventFactory<TransactionExportEvent>{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportEventFactory() {
		super();
	}
	
	@Override
	public TransactionExportEvent newInstance() {
		return new TransactionExportEvent();
	}
	
}
