package com.stargazerproject.bus.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.base.impl.BusImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.base.impl.BaseEvent;

@Component(value="eventBusImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("eventBusImpl")
public class EventBus extends BusImpl<BaseEvent> implements StanderCharacteristicShell<Bus<BaseEvent>>{

	public EventBus() {
		super();
		}

	@Override
	public void initialize(Optional<Bus<BaseEvent>> busArg) {
		bus = busArg.get();
	}

}
