package com.stargazerproject.bus.base.impl;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

public abstract class BusImpl<T> implements Bus<T>{
	
	protected Bus<T> bus;

	@Override
	public Optional<T> push(Optional<T> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException{
		return bus.push(busEvent, timeUnit, timeout);
	}
	
	@Override
	public Optional<BusObserver<T>> pushNoBlock(Optional<T> busEvent) {
		return bus.pushNoBlock(busEvent);
	}

	@Override
	public void startBus() {
		bus.startBus();
	}

	@Override
	public void stopBus() {
		bus.startBus();
	}

}
