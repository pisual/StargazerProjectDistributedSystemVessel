package com.stargazerproject.messagequeue;

import java.util.List;

import com.google.common.base.Optional;

public interface MessageQueuePush<T> {
	
	/**
	* @name 批量推送
	* @illustrate 批量推送内容
	* @param <Integer> 批量推送的内容
	* **/
	public void put(Optional<List<T>> t);
	
}
