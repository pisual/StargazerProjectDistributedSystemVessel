package com.stargazerproject.queue;

import com.google.common.base.Optional;

/** 
 *  @name 队列消费者
 *  @illustrate 队列操作的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface QueueConsumer<E> {
	
	/**
	* @name 消费
	* @illustrate 队列Entry内容置入
	* @param <K> 队列Entry值
	* @Optional
	* **/
	public void consumer(Optional<E> e);
}
