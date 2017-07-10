package com.stargazerproject.queue.disruptor.resources.queue;

import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.stargazerproject.queue.Queue;

public abstract class BaseQueueDisruptor<E> implements Queue<E>{
	
	protected Disruptor<E> disruptor;
	
	protected WorkHandler<E>[] handler;

	protected BaseQueueDisruptor() {}
	
	@Override
	public void start() {
		disruptor.start();
	}

	@Override
	public void shutdown() {
		disruptor.shutdown();
	}
}