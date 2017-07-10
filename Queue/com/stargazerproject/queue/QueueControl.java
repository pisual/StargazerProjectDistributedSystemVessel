package com.stargazerproject.queue;

/** 
 *  @name 队列控制接口
 *  @illustrate 队列控制的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface QueueControl<E> {
	/**
	* @name 启动队列
	* @illustrate 启动队列
	* **/
	public void start();
	
	/**
	* @name 关闭队列
	* @illustrate 启动队列
	* **/
	public void shutdown();
}
