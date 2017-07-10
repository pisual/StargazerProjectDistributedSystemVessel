package com.stargazerproject.queue.disruptor.resources.queue;

/** 
 *  @name 队列中处理方法接口
 *  @illustrate 队列的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface DisruptorQueueHandle<E> {
	
	/**
	* @name 处理方法
	* @illustrate 队列Entry内容置入
	* **/
	public void handleEvents();
}
