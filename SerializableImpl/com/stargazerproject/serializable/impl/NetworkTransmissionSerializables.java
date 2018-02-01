package com.stargazerproject.serializable.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.serializable.Serializables;
import com.stargazerproject.serializable.base.impl.BaseSerializablesImpl;

@Component(value="networkTransmissionSerializables")
@Qualifier("networkTransmissionSerializables")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NetworkTransmissionSerializables extends BaseSerializablesImpl implements StanderCharacteristicShell<Serializables>, BeforehandCharacteristicShell<Serializables>{

	public NetworkTransmissionSerializables() {}
	
	@Override
	public void initialize(Optional<Serializables> serializablesArg) {
		serializables = serializablesArg.get();
	}

	@Override
	@Qualifier("networkTransmissionSerializablesShell")
	@Autowired
	public void initialize(BaseCharacteristic<Serializables> serializablesArg) {
		serializables = serializablesArg.characteristic().get();
	}
	
}
