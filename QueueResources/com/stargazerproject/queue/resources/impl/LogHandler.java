package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.consumer.impl.LogConsumer;
import com.stargazerproject.queue.model.LogQueueEvent;

/** 
 *  @name Log队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("logHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogHandler extends LogConsumer implements WorkHandler<LogQueueEvent>{
	
	/** @construction 初始化构造 **/
	public LogHandler() {}

	@Override
	public void onEvent(LogQueueEvent event){
		super.consumer(Optional.of(event.getLogData()));
	}
}
