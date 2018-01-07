package com.stargazerproject.service.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceAnnotationResources;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceResourcesResouecesCharacteristic")
@Qualifier("serviceResourcesResouecesCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceResourcesResouecesCharacteristic implements ServiceAnnotationResources, BaseCharacteristic<ServiceAnnotationResources> {
	
	private Optional<List<AbstractIdleService>> serviceList;
	
	@Override
	public Optional<ServiceAnnotationResources> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<List<AbstractIdleService>> allServiceList() {
		return serviceList;
	}

	@Override
	public void initializationServiceList(Optional<List<String>> serviceListArg) {
		serviceList = Optional.of(new ArrayList<AbstractIdleService>());
		serviceListArg.get().forEach(x -> serviceList.get().add(BeanContainer.instance().getBean(Optional.of(x), AbstractIdleService.class)));
	}
	
}