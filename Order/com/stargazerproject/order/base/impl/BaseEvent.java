package com.stargazerproject.order.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.EventState;

/** 
 *  @name 事件（BaseEvent）模型
 *  @illustrate 事件（BaseEvent）模型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseEvent extends ID implements Event{
	
	private static final long serialVersionUID = 1122535382679831080L;
	
	protected Event event;
	
	protected BaseEvent() {}

	@Override
	public void startEvent(Optional<EventAnalysis> eventAnalysis) {
		event.startEvent(eventAnalysis);
	}

	@Override
	public void skipEvent() {
		event.skipEvent();
	}

	@Override
	public void eventResult(Optional<EventResultAnalysis> eventResultAnalysis) {
		event.eventResult(eventResultAnalysis);
	}

	@Override
	public Optional<EventState> eventState() {
		return event.eventState();
	}
	
}