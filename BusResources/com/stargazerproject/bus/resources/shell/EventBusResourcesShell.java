package com.stargazerproject.bus.resources.shell;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusEventListen;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;

@Component(value="eventBusResourcesShell")
@Qualifier("eventBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusResourcesShell implements Bus<Event>, BaseCharacteristic<Bus<Event>>{
	
	@Autowired
	@Qualifier("eventBusBlockMethodCharacteristic")
	private BaseCharacteristic<BusBlockMethod<Event>> eventBusBlockMethodCharacteristic;
	
	@Autowired
	@Qualifier("eventBusNoBlockMethodCharacteristic")
	private BaseCharacteristic<BusNoBlockMethod<Event>> eventBusNoBlockMethodCharacteristic;
	
	private BusBlockMethod<Event> busBlockMethod;
	
	private BusNoBlockMethod<Event> busNoBlockMethod;
	
	public EventBusResourcesShell() {}
	
	@Override
	public Optional<Bus<Event>> characteristic() {
		busBlockMethod = eventBusBlockMethodCharacteristic.characteristic().get();
		busNoBlockMethod = eventBusNoBlockMethodCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<Event> push(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		return busBlockMethod.push(busEvent, timeUnit, timeout);
	}
	
	@Override
	public Optional<BusObserver<Event>> pushNoBlock(Optional<Event> busEvent, Optional<BusEventListen> BusEventListen, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		return busNoBlockMethod.pushNoBlock(busEvent, BusEventListen, timeUnit, timeout);
	}

	@Override
	public void startBus() {
		
	}

	@Override
	public void stopBus() {
		
	}
	
}