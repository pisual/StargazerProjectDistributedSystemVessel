package com.stargazerproject.messagequeue;

import java.util.List;

import com.google.common.base.Optional;

public interface MessageQueueAcquire<T> {
	
	/**
	* @name 批量抓取
	* @illustrate 批量抓取内容
	* @param <Integer> 批量抓取的数目
	* @param List<T> 获取批量抓取的结果
	* **/
	public Optional<List<T>> get(Optional<Integer> messageNumber);
	
}
