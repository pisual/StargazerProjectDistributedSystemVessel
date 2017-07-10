package com.stargazerproject.service;

import java.util.List;

import com.google.common.util.concurrent.AbstractIdleService;

public interface ServiceResources {
	public List<AbstractIdleService> allServiceList();
}
