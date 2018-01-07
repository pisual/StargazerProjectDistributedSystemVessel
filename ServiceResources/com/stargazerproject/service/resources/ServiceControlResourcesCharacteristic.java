package com.stargazerproject.service.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceAnnotationResources;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceControlResourcesCharacteristic")
@Qualifier("serviceControlResourcesCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceControlResourcesCharacteristic implements ServiceControl, BaseCharacteristic<ServiceControl>{
	
	@Autowired
	@Qualifier("serviceResourcesResouecesCharacteristic")
	private BaseCharacteristic<ServiceAnnotationResources> serviceResourcesResouecesCharacteristic;
	
	private ServiceManager serviceManager;
	private ServiceAnnotationResources serviceResources;
	
	public ServiceControlResourcesCharacteristic() {}
	
	@Override
	public Optional<ServiceControl> characteristic() {
		serviceResources = serviceResourcesResouecesCharacteristic.characteristic().get();
		serviceManager = new ServiceManager(serviceResources.allServiceList().get());
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

}