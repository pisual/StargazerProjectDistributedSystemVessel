package com.stargazerproject.service.resources.ModuleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="moduleServiceServiceManager")
@Qualifier("moduleServiceServiceManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ModuleServiceServiceManager implements BaseCharacteristic<ServiceManager> {
	
	private ServiceManager serviceManager;
	
	@Override
	@Bean(name="moduleServiceServiceManagerCharacteristic")
	@Lazy(true)
	public Optional<ServiceManager> characteristic() {
		Optional<List<AbstractIdleService>> serviceList = BeanContainer.instance().getBean(Optional.of("serviceListCharacteristic"), Optional.class);
		serviceManager = new ServiceManager(serviceList.get());
		return Optional.of(serviceManager);
	}
	

}