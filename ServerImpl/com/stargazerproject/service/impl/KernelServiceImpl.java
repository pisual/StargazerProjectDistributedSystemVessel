package com.stargazerproject.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.Server;
import com.stargazerproject.service.base.impl.BaseServiceImpl;

@Component(value="kernelService")
@Qualifier("kernelService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KernelServiceImpl extends BaseServiceImpl implements StanderCharacteristicShell<Server>{

	private KernelServiceImpl() {}
	
	@Override
	public void initialize(Optional<Server> serverArg) {
		server = serverArg.get();
	}

}
