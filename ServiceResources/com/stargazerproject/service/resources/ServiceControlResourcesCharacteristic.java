package com.stargazerproject.service.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceResources;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceControlResources")
@Qualifier("serviceControlResources")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceControlResourcesCharacteristic implements ServiceControl, BaseCharacteristic<ServiceControl>{
	
	private ServiceManager serviceManager;
	private Optional<ServiceResources> serviceResources;
	
	public ServiceControlResourcesCharacteristic() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="serviceControlResourcesCharacteristic")
	@Lazy(true)
	public Optional<ServiceControl> characteristic() {
		serviceResources = BeanContainer.instance().getBean(Optional.of("serviceResourcesResouecesCharacteristic"), Optional.class);
		serviceManager = new ServiceManager(serviceResources.get().allServiceList().get());
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