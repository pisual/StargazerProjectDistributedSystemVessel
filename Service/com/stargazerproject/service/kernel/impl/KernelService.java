package com.stargazerproject.service.kernel.impl;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.impl.Service;

public class KernelService extends Service implements StanderCharacteristicShell<ServiceManager>{

	private KernelService() {}
	
	@Override
	public void initialize(Optional<ServiceManager> serviceManagerArg) {
		serviceManager = serviceManagerArg.get();
	}

}
