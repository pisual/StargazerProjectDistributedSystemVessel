package com.stargazerproject.service.impl;

import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.service.ServiceControl;

public abstract class Service implements ServiceControl {
	
	protected static ServiceManager serviceManager;
	
	protected Service() {}

	@Override
	public void startAllservice() {
		serviceManager.startAsync().awaitHealthy();
	}

	@Override
	public void stopAllService() {
		serviceManager.stopAsync().awaitStopped();
	}
	
}