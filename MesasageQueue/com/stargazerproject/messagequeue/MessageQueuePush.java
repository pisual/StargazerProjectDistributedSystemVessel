package com.stargazerproject.messagequeue;

public interface MessageQueuePush<T> {
	
	public void put(T t);
	
}
