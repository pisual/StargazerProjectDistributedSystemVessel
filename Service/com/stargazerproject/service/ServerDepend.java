package com.stargazerproject.service;

import com.google.common.base.Optional;

public interface ServerDepend {
	
	public Optional<Boolean> dependOnDelay(Optional<String> workInServiceStates);
}
