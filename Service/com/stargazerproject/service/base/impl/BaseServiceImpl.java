package com.stargazerproject.service.base.impl;

import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Table;
import com.stargazerproject.service.Service;

public abstract class BaseServiceImpl implements Service {
	
	protected Service service;

	@Override
	public void startAllservice() {
		service.startAllservice();
	}

	@Override
	public void stopAllService() {
		service.stopAllService();
	}

	@Override
	public void initializationFromAnnotationsScan() {
		service.initializationFromAnnotationsScan();
	}

	@Override
	public void initializationFromServerMenu(Optional<Map<String, Integer>> serverMenu) {
		service.initializationFromServerMenu(serverMenu);
	}
	
	@Override
	public Optional<Table<Integer, String, Boolean>> serviceMenu() {
		return service.serviceMenu();
	}
	
}