package com.stargazerproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.Service;
import com.stargazerproject.service.base.impl.BaseServiceImpl;

@Component(value="kernelService")
@Qualifier("kernelService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KernelServiceImpl extends BaseServiceImpl implements StanderCharacteristicShell<Service>, BeforehandCharacteristicShell<Service>{

	private KernelServiceImpl() {}
	
	@Override
	public void initialize(Optional<Service> serverArg) {
		service = serverArg.get();
	}

	@Override
	@Qualifier("serverShell")
	@Autowired
	public void initialize(BaseCharacteristic<Service> serverArg) {
		service = serverArg.characteristic().get();
	}

}
