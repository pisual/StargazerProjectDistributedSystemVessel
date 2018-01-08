package com.stargazerproject.service.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServerDepend;

@Component(value="serverDependCharacteristic")
@Qualifier("serverDependCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerDependCharacteristic implements ServerDepend, BaseCharacteristic<ServerDepend>{
	
	@Autowired
	@Qualifier("serverCache")
	private Cache<String, Boolean> serverCache;
	
	@Override
	public Optional<ServerDepend> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public Optional<Boolean> dependOnDelay(Optional<String> workInServiceStates) {
		return serverCache.get(workInServiceStates);
	}
	
}