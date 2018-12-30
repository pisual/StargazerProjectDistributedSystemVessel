package com.stargazerproject.service.resources.shell;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Table;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.Service;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceInitialization;

@Component(value="serverShell")
@Qualifier("serverShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerShell implements Service, BaseCharacteristic<Service>{
	
	@Autowired
	@Qualifier("serviceControlCharacteristic")
	private BaseCharacteristic<ServiceControl> serviceControlCharacteristic;
	
	@Autowired
	@Qualifier("serviceInitializationCharacteristic")
	private BaseCharacteristic<ServiceInitialization> serverInitializationCharacteristic;
	
	
	private ServiceControl serviceControl;
	
	private ServiceInitialization serverInitialization;
	
	
	public ServerShell() {}
	
	@Override
	public Optional<Service> characteristic() {
		serviceControl = serviceControlCharacteristic.characteristic().get();
		serverInitialization = serverInitializationCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public void startAllservice() {
		serviceControl.startAllservice();
	}

	@Override
	public void stopAllService() {
		serviceControl.stopAllService();
	}

	@Override
	public void initializationFromAnnotationsScan() {
		serverInitialization.initializationFromAnnotationsScan();
	}
	@Override
	public void initializationFromServerMenu(Optional<Map<String, Integer>> serverMenu) {
		serverInitialization.initializationFromServerMenu(serverMenu);
	}

	@Override
	public Optional<Table<Integer, String, Boolean>> serviceMenu() {
		return serverInitialization.serviceMenu();
	}

}
