package com.stargazerproject.service.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Table;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceInitialization;

@Component(value="serviceInitializationCharacteristic")
@Qualifier("serviceInitializationCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceInitializationCharacteristic implements ServiceInitialization, BaseCharacteristic<ServiceInitialization>{
	
	@Autowired
	@Qualifier("systemServiceParameterList")
	private BaseCharacteristic<Table<Integer, String, Boolean>> systemServiceterListCharacteristic;
	
	private Table<Integer, String, Boolean> systemServiceterList;
	
	@Override
	public Optional<ServiceInitialization> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void initializationFromAnnotationsScan() {
		systemServiceterList = systemServiceterListCharacteristic.characteristic().get();
	}
	
	@Override
	public void initializationFromServerMenu(Optional<Map<String, Integer>> serverMenu) {
		
	}
	
	public Optional<Table<Integer, String, Boolean>> serviceMenu(){
		return Optional.of(systemServiceterList);
	}
	
}