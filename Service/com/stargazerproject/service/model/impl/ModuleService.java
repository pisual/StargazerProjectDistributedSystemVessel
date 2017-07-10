package com.stargazerproject.service.model.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.impl.Service;

@Component
@Qualifier("moduleService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ModuleService extends Service implements StanderCharacteristicShell<ServiceManager>{

	private ModuleService() {}
	
	@Override
	@Resource(name="moduleServiceServiceManagerCharacteristic")
	public void initialize(Optional<ServiceManager> serviceManagerArg) {
		serviceManager = serviceManagerArg.get();
	}

}