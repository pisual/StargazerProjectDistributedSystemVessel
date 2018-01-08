package com.stargazerproject.service.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.service.Server;

public abstract class BaseServiceImpl implements Server {
	
	protected Server server;
	
	private Boolean initializationState = Boolean.FALSE;

	@Override
	public void startAllservice() {
		server.startAllservice();
	}

	@Override
	public void stopAllService() {
		server.stopAllService();
	}
	
	@Override
	public Optional<Boolean> dependOnDelay(Optional<String> workInServiceStates) {
		return server.dependOnDelay(workInServiceStates);
	}
	
	@Override
	public Optional<List<String>> initializationFromSequenceFile(Optional<String> filePath) {
		initializationStateCheck();
		return (server.initializationFromSequenceFile(filePath));
	}
	
	@Override
	public Optional<List<String>> initializationFromAnnotationsScan() {
		initializationStateCheck();
		return(server.initializationFromAnnotationsScan());
	}
	
	private void initializationStateCheck(){
		if(initializationState.equals(Boolean.TRUE)){
			throw new IllegalArgumentException("The service has been initialized.");
		}
	}
	
}