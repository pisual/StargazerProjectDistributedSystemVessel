package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventState;

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
	public void eventExecute(Optional<EventExecuteAnalysis> eventAnalysis) {
		event.eventExecute(eventAnalysis);
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

	@Override
	public void eventAssemble(Optional<EventAssembleAnalysis> eventAssembleAnalysis) {
		event.eventAssemble(eventAssembleAnalysis);
	}
	
}