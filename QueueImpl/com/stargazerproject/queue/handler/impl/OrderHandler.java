package com.stargazerproject.queue.handler.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.model.order.impl.Order;

/** 
 *  @name Log队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("orderHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderHandler implements WorkHandler<Order>{
	
	/** @construction 初始化构造 **/
	public OrderHandler() {}

	@Override
	public void onEvent(Order event){
		System.out.println("start onEvent");
		
		System.out.println("end onEvent");
	}
}
