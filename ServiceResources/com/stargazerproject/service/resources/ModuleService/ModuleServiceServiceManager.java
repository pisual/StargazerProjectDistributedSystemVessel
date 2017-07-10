package com.stargazerproject.service.resources.ModuleService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component
@Qualifier("moduleServiceServiceManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ModuleServiceServiceManager implements BaseCharacteristic<ServiceManager>, StanderCharacteristicShell<List<AbstractIdleService>> {
	
	private ServiceManager serviceManager;
	
	@Override
	@Bean(name="moduleServiceServiceManagerCharacteristic")
	public Optional<ServiceManager> characteristic() {
		return Optional.of(serviceManager);
	}
	
	@Override
	@Autowired
	@Resource(name="serviceListCharacteristic")
	public void initialize(Optional<List<AbstractIdleService>> serviceList) {
		serviceManager = new ServiceManager(serviceList.get());
	}

}