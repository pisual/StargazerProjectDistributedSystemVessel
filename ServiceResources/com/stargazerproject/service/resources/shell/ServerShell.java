package com.stargazerproject.service.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.Server;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceAnnotationResources;

@Component(value="serverShell")
@Qualifier("serverShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerShell implements Server, BaseCharacteristic<Server>{
	
	@Autowired
	@Qualifier("serviceControlResourcesCharacteristic")
	private BaseCharacteristic<ServiceControl> serviceControlResourcesCharacteristic;
	
	@Autowired
	@Qualifier("serviceResourcesResouecesCharacteristic")
	private BaseCharacteristic<ServiceAnnotationResources> serviceResourcesResouecesCharacteristic;
	
	private ServiceControl serviceControl;
	
	private ServiceAnnotationResources serviceResources;
	
	public ServerShell() {}
	
	@Override
	public Optional<Server> characteristic() {
		serviceControl = serviceControlResourcesCharacteristic.characteristic().get();
		serviceResources = serviceResourcesResouecesCharacteristic.characteristic().get();
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
	public Optional<List<AbstractIdleService>> allServiceList() {
		return serviceResources.allServiceList();
	}

	@Override
	public void initializationServiceList(Optional<List<String>> serviceList) {
		serviceResources.initializationServiceList(serviceList);
	}

}
