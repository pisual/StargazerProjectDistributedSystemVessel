package com.stargazerproject.queue.resources;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventTranslatorOneArg;

public abstract class BaseQueueRingBuffer<E> extends BaseQueueDisruptor<E>{
	
	private EventTranslatorOneArg<E, E> translator;
	
	protected BaseQueueRingBuffer() {
		translator = new EventTranslatorOneArg<E, E>() {
			public void translateTo(E e, long sequence, E eArg) {
				e = eArg;
			}
		};
	}
	
	@Override
	public void producer(Optional<E> e) {
		disruptor.getRingBuffer().publishEvent(translator,e.get());
	}
	
}