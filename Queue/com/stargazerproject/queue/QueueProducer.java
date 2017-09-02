package com.stargazerproject.queue;

import com.google.common.base.Optional;

/** 
 *  @name 队列生产者
 *  @illustrate 队列生产者的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface QueueProducer<E>{
	
	/**
	* @name 置入
	* @illustrate 队列Entry内容置入
	* @param <K> 队列Entry值
	* @Optional
	* **/
	public void producer(Optional<E> e);
}
