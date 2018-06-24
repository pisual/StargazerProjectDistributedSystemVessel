package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.queue.model.TransactionExportEvent;

/** 
 *  @name TransactionExportEvent队列的清除Handler
 *  @illustrate 用于清除TransactionExportEvent中消费过的Event
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="cleanTransactionExportEventHandler")
@Qualifier("cleanTransactionExportEventHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CleanTransactionExportEventHandler implements WorkHandler<TransactionExportEvent> {
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public CleanTransactionExportEventHandler() {}

	@Override
	public void onEvent(TransactionExportEvent event){
		event.clear();
	}
	
}
