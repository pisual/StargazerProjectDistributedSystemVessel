package com.stargazerproject.bus.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.base.impl.BusImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Event;

public class EventBus extends BusImpl<Event> implements StanderCharacteristicShell<Bus<Event>>{

	public EventBus() {super();}

	@Override
	public void initialize(Optional<Bus<Event>> busArg) {
		bus = busArg.get();
	}

}
