package com.stargazerproject.bus.base.impl;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusEventListen;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

public abstract class BusImpl<T> implements Bus<T>{
	
	protected Bus<T> bus;

	@Override
	public Optional<T> push(Optional<T> busEvent, TimeUnit timeUnit, int timeout) throws BusEventTimeoutException{
		return bus.push(busEvent, timeUnit, timeout);
	}
	
	@Override
	public Optional<BusObserver<T>> pushNoBlock(Optional<T> busEvent, Optional<BusEventListen> BusEventListen, TimeUnit timeUnit, int timeout) {
		return bus.pushNoBlock(busEvent, BusEventListen, timeUnit, timeout);
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
