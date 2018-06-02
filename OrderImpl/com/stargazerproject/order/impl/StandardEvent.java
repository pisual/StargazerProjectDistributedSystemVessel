package com.stargazerproject.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.base.impl.BaseEvent;

@Component(value="standardEvent")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("standardEvent")
public class StandardEvent extends BaseEvent implements StanderCharacteristicShell<Event>{

	private static final long serialVersionUID = 9027890577069473120L;

	@Override
	@Qualifier("standardEventShell")
	@Autowired
	public void initialize(Optional<Event> eventArg) {
		event = eventArg.get();
	}

}
