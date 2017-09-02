package com.stargazerproject.queue.resources;

import com.lmax.disruptor.EventFactory;

public abstract class QueueEventFactory<E> implements EventFactory<E> {
	
	protected QueueEventFactory() {}
	
}