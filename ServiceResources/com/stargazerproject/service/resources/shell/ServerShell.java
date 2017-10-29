package com.stargazerproject.service.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.service.Server;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceResources;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serverShell")
@Qualifier("serverShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerShell implements Server, BaseCharacteristic<Server>{
	
	private Optional<ServiceControl> serviceControl;
	private Optional<ServiceResources> serviceResources;
	
	public ServerShell() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="serverShellCharacteristic")
	@Lazy(true)
	public Optional<Server> characteristic() {
		serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlResourcesCharacteristic"), Optional.class);
		serviceResources = BeanContainer.instance().getBean(Optional.of("serviceResourcesResouecesCharacteristic"), Optional.class);
		return Optional.of(this);
	}

	@Override
	public void startAllservice() {
		serviceControl.get().startAllservice();
	}

	@Override
	public void stopAllService() {
		serviceControl.get().stopAllService();
	}

	@Override
	public Optional<List<AbstractIdleService>> allServiceList() {
		return serviceResources.get().allServiceList();
	}

	@Override
	public void initializationServiceList(Optional<List<String>> serviceList) {
		serviceResources.get().initializationServiceList(serviceList);
	}

}
