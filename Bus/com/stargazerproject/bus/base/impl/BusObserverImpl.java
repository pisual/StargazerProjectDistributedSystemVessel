package com.stargazerproject.bus.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;

public abstract class BusObserverImpl<T> implements BusObserver<T>{

	protected T event;
	
	public BusObserverImpl(Optional<T> eventArg) {
		event = eventArg.get();
	}
	
	@Override
	public Optional<T> resultEvent() {
		return Optional.of(event);
	}

}
