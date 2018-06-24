package com.stargazerproject.bus.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.bus.base.impl.BusObserverImpl;
import com.stargazerproject.transaction.Event;

public class EventBusObserver extends BusObserverImpl<Event>{
	
	@Qualifier("eventBusAnalysisImpl")
	@Autowired
	private EventExecuteAnalysis eventExecuteAnalysis;

	public EventBusObserver(Optional<Event> eventArg) {
		super(eventArg);
	}
	
	@Override
	public boolean isComplete() {
		return event.eventResult(Optional.of(eventExecuteAnalysis));
	}

	@Override
	public void skip() {
		event.skipEvent();
	}

}
