package com.stargazerproject.service.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServerInitialization;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceControlCharacteristic")
@Qualifier("serviceControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceControlCharacteristic implements ServiceControl, BaseCharacteristic<ServiceControl>{
	
	@Autowired
	@Qualifier("serverInitializationCharacteristic")
	private BaseCharacteristic<ServerInitialization> serverInitializationCharacteristic;
	
	private ServiceManager serviceManager;
	
	public ServiceControlCharacteristic() {}
	
	@Override
	public Optional<ServiceControl> characteristic() {
		Optional<List<String>> serviceListArg = serverInitializationCharacteristic.characteristic().get().initializationFromAnnotationsScan();
		serviceManager = new ServiceManager(serviceListConvertToAbstractIdleServiceList(serviceListArg).get());
		return Optional.of(this);
	}

	@Override
	public void startAllservice() {
		serviceManager.startAsync().awaitHealthy();
	}

	@Override
	public void stopAllService() {
		serviceManager.stopAsync().awaitStopped();
	}
	
	private Optional<List<AbstractIdleService>> serviceListConvertToAbstractIdleServiceList(Optional<List<String>> serviceListArg){
		List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
		serviceListArg.get().forEach(x -> serviceList.add(BeanContainer.instance().getBean(Optional.of(x), AbstractIdleService.class)));
		return Optional.of(serviceList);
	}

}