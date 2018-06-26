package com.stargazerproject.bus.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventBusResultAnalysisExtend;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.ResultState;

@Component(value="eventBusObserverShell")
@Qualifier("eventBusObserverShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusObserverShell implements BusObserver<Event> ,BaseCharacteristic<BusObserver<Event>>{

	private Event event;
	
	@Qualifier("eventBusResultAnalysisImpl")
	@Autowired
	private EventBusResultAnalysisExtend eventBusResultAnalysisExtend;
	
	@Override
	@Qualifier("eventBusObserverShellCharacteristic")
	public Optional<BusObserver<Event>> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<Boolean> isComplete() {
		event.eventResult(Optional.of(eventBusResultAnalysisExtend));
		ResultState resultState = eventBusResultAnalysisExtend.resultState().get();
		return (resultState == ResultState.SUCCESS) ? Optional.of(Boolean.TRUE) : Optional.of(Boolean.FALSE);
	}

	@Override
	public Optional<Event> resultEvent() {
		return Optional.of(event);
	}

	@Override
	public void injectEvent(Optional<Event> eventArg) {
		event = eventArg.get();
	}

	@Override
	public void skip() {
		event.skipEvent();
	}


}
