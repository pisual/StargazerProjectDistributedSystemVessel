package com.stargazerproject.queue.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.queue.Queue;

/** 
 *  @name 基础队列
 *  @illustrate 基础队列
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public abstract class StandQueue<E> implements Queue<E>{

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
	public void producer(Optional<E> e) {
		queue.producer(e);
	}

}