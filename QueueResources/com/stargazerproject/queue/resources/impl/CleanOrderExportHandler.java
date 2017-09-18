package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.queue.model.OrderExportEvent;

/** 
 *  @name Event队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("cleanOrderExportHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CleanOrderExportHandler implements WorkHandler<OrderExportEvent> {
	
	/** @construction 初始化构造 **/
	public CleanOrderExportHandler() {}

	@Override
	public void onEvent(OrderExportEvent event){
		event.clear();
	}
	
}
