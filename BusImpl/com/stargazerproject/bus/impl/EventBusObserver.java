package com.stargazerproject.bus.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.base.impl.BusObserverImpl;
import com.stargazerproject.order.impl.Event;

public class EventBusObserver extends BusObserverImpl<Event>{

	public EventBusObserver(Optional<Event> eventArg) {
		super(eventArg);
	}
	
	@Override
	public boolean isComplete() {
		return event.isComplete();
	}

	@Override
	public void skip() {
		event.skipEvent();
	}

}
