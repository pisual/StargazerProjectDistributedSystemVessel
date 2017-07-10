package com.stargazerproject.messagequeue.base.impl;

import java.util.List;

import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueControl;

public abstract class BaseMessageQueue<T> implements MessageQueue<T>{
	
	protected MessageQueue<T>  messageQueue;
	
	protected BaseMessageQueue() {}
	
	@Override
	public MessageQueueControl<T> Join() {
		return messageQueue.Join();
	}
	
	@Override
	public void put(T t) {
		messageQueue.put(t);
	}
	
	@Override
	public List<T> get(int messageNumber) {
		return messageQueue.get(messageNumber);
	}
	
	
}
