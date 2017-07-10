package com.stargazerproject.queue;

import com.google.common.base.Optional;

/** 
 *  @name 队列序列操作接口
 *  @illustrate 队列序列操作的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface QueueOperation<E>{
	
	/**
	* @name 置入
	* @illustrate 队列Entry内容置入
	* @param <K> 队列Entry值
	* @Optional
	* **/
	public void put(Optional<E> e);
}
