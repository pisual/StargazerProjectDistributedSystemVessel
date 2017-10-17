package com.stargazerproject.messagequeue.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.messagequeue.MessageQueue;

public class BaseMessageQueue<T> implements MessageQueue<T>{
	
	protected MessageQueue<T>  messageQueue;
	
	protected BaseMessageQueue() {}

	@Override
	public Optional<List<T>> get(Optional<Integer> messageNumber) {
		return messageQueue.get(messageNumber);
	}

	@Override
	public void join() {
		messageQueue.join();
	}

	@Override
	public void out() {
		messageQueue.out();
	}

	@Override
	public void put(Optional<List<T>> t) {
		messageQueue.put(t);
	}
	
}
