package com.stargazerproject.service.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.service.Server;

public abstract class BaseServiceImpl implements Server {
	
	protected Server server;

	@Override
	public void startAllservice() {
		server.startAllservice();
	}

	@Override
	public void stopAllService() {
		server.stopAllService();
	}

	@Override
	public Optional<List<AbstractIdleService>> allServiceList() {
		return server.allServiceList();
	}

	@Override
	public void initializationServiceList(Optional<List<String>> serviceList) {
		server.initializationServiceList(serviceList);
	}
	
}