package com.stargazerproject.queue.resources;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventTranslatorOneArg;

public abstract class BaseQueueRingBuffer<E, V> extends BaseQueueDisruptor<E, V>{
	
	protected EventTranslatorOneArg<V, E> translator;
	
	protected BaseQueueRingBuffer() {
		translator = new EventTranslatorOneArg<V, E>() {
			public void translateTo(V v, long sequence, E a) {

			}
		};
	}
	
	@Override
	public void producer(Optional<E> e) {
		disruptor.getRingBuffer().publishEvent(translator,e.get());
	}
	
}