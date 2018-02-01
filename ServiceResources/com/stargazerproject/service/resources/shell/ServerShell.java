package com.stargazerproject.service.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.Server;
import com.stargazerproject.service.ServerDepend;
import com.stargazerproject.service.ServerInitialization;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.aop.configuration.ServerDependDetectionAOPConfiguration;

@Component(value="serverShell")
@Qualifier("serverShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServerShell implements Server, BaseCharacteristic<Server>{
	
	@Autowired
	@Qualifier("serviceControlCharacteristic")
	private BaseCharacteristic<ServiceControl> serviceControlCharacteristic;
	
	@Autowired
	@Qualifier("serverInitializationCharacteristic")
	private BaseCharacteristic<ServerInitialization> serverInitializationCharacteristic;
	
	@Autowired
	@Qualifier("serverDependCharacteristic")
	private BaseCharacteristic<ServerDepend> serverDependCharacteristic;
	
	@Autowired
	@Qualifier("serverDependDetectionAOPConfiguration")
	private ServerDependDetectionAOPConfiguration serverDependDetectionAOPConfiguration;
	
	private ServiceControl serviceControl;
	
	private ServerInitialization serverInitialization;
	
	private ServerDepend serverDepend;
	
	public ServerShell() {}
	
	@Override
	public Optional<Server> characteristic() {
		serverDepend = serverDependCharacteristic.characteristic().get();
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
	public Optional<Boolean> dependOnDelay(Optional<String> workInServiceStates) {
		return serverDepend.dependOnDelay(workInServiceStates);
	}

	@Override
	public Optional<List<String>> initializationFromSequenceFile(Optional<String> filePath) {
		return serverInitialization.initializationFromSequenceFile(filePath);
	}

	@Override
	public Optional<List<String>> initializationFromAnnotationsScan() {
		return serverInitialization.initializationFromAnnotationsScan();
	}

}
