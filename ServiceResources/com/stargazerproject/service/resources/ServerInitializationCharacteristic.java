package com.stargazerproject.service.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServerInitialization;

@Component(value="serverInitializationCharacteristic")
@Qualifier("serverInitializationCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerInitializationCharacteristic implements ServerInitialization, BaseCharacteristic<ServerInitialization>{
	
	@Autowired
	@Qualifier("serverCache")
	private Cache<String, Boolean> serverCache;
	
	@Autowired
	@Qualifier("serviceParameterList")
	private BaseCharacteristic<List<String>> serviceParameterList;
	
	@Override
	public Optional<ServerInitialization> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public Optional<List<String>> initializationFromSequenceFile(Optional<String> filePath) {
		return null;
	}
	
	@Override
	public Optional<List<String>> initializationFromAnnotationsScan() {		
		serviceParameterList.characteristic().get().stream().map(x -> x.replace("Manage", "")).collect(Collectors.toList())
		                                           .stream().forEach(x -> serverCache.put(Optional.of(x), Optional.of(Boolean.FALSE)));
		return serviceParameterList.characteristic();
	}
	
}