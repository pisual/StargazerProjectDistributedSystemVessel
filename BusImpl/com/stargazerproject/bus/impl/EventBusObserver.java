package com.stargazerproject.bus.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.base.impl.BusObserverImpl;
import com.stargazerproject.transaction.base.impl.BaseEvent;

public class EventBusObserver extends BusObserverImpl<BaseEvent>{

	public EventBusObserver(Optional<BaseEvent> eventArg) {
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
