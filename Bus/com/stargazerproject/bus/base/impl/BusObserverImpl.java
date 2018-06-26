package com.stargazerproject.bus.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.transaction.Event;

public class BusObserverImpl<T> implements BusObserver<T>{

	protected BusObserver<T> busObserver;

	@Override
	public Optional<Boolean> isComplete() {
		return busObserver.isComplete();
	}

	@Override
	public Optional<T> resultEvent() {
		return busObserver.resultEvent();
	}
	
	@Override
	public void injectEvent(Optional<Event> event) {
		busObserver.injectEvent(event);
	}

	@Override
	public void skip() {
		busObserver.skip();
	}

}
