package com.stargazerproject.queue;

import com.google.common.base.Optional;

/** 
 *  @name 队列生产操作接口
 *  @illustrate 队列操作的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface QueueProduce<E> {
	
	/**
	* @name 生产操作
	* @illustrate 队列Entry内容置入
	* @param <K> 队列Entry值
	* @Optional
	* **/
	public void onData(Optional<E> e);
}
