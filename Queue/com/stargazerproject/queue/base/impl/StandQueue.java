package com.stargazerproject.queue.base.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.queue.QueueProduce;

/** 
 *  @name 基础队列
 *  @illustrate 基础队列
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("standQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public abstract class StandQueue<E> implements QueueControl<E>, QueueProduce<E>{

	/** @illustrate 通用Queue接口 **/
	protected Queue<E> queue;
	
	/** @construction 初始化构造 **/
	protected StandQueue() {}
	
	/** @illustrate 启动队列 **/
	@Override
	public void start(){
		queue.start();
	}
	
	/** @illustrate 关闭队列 **/
	@Override
	public void shutdown(){
		queue.shutdown();;
	}
	
	@Override
	public void onData(Optional<E> e) {
		queue.put(e);
	}

}