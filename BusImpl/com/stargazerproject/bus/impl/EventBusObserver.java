package com.stargazerproject.bus.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.base.impl.BusObserverImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Event;

@Component(value="eventBusObserver")
@Qualifier("eventBusObserver")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusObserver extends BusObserverImpl<Event> implements StanderCharacteristicShell<BusObserver<Event>>{

	@Override
	@Qualifier("eventBusObserverShellCharacteristic")
	@Autowired
	public void initialize(Optional<BusObserver<Event>> busObserverArg) {
		busObserver = busObserverArg.get();
	}
	
}
