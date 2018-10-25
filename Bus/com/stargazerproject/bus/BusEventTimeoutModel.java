package com.stargazerproject.bus;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;

public class BusEventTimeoutModel {

	private TimeUnit timeUnit;
	private Integer timeout;
	
	public BusEventTimeoutModel(Optional<TimeUnit> timeUnitArg, Optional<Integer> timeoutArg) {
		timeUnit = timeUnitArg.get();
		timeout = timeoutArg.get();
	}
	
	/**
	 * @return the Optional.of(timeUnit)
	 */
	public Optional<TimeUnit> getTimeUnit() {
		return Optional.of(timeUnit);
	}
	/**
	 * @return the Optional.of(timeout)
	 */
	public Optional<Integer> getTimeout() {
		return Optional.of(timeout);
	}
	
	
	
}
