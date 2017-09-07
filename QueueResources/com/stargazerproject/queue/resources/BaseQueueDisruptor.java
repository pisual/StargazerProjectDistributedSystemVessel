package com.stargazerproject.queue.resources;

import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.stargazerproject.queue.Queue;

public abstract class BaseQueueDisruptor<E,V> implements Queue<E>{
	
	protected Disruptor<V> disruptor;
	
	protected WorkHandler<V>[] handler;

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