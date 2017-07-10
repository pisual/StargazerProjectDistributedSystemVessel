package com.stargazerproject.queue.handler.base.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;

/** 
 *  @name 队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("queueHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class QueueHandler<E> implements WorkHandler<E>{

	/** @construction 初始化构造 **/
	protected QueueHandler() {}

	@Override
	public void onEvent(E e){
		System.out.println("start onEvent");
		System.out.println("end onEvent");
	}

}
