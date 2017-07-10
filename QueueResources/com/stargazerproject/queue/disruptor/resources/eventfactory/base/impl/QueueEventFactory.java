package com.stargazerproject.queue.disruptor.resources.eventfactory.base.impl;

import com.lmax.disruptor.EventFactory;

public abstract class QueueEventFactory<E> implements EventFactory<E> {
	
	protected QueueEventFactory() {}
	
}