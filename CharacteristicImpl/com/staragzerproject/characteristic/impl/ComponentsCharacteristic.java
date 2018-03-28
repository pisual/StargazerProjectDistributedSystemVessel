package com.staragzerproject.characteristic.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.Characteristic;
import com.stargazerproject.characteristic.base.impl.BaseCharacteristicImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="componentsCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("componentsCharacteristic")
public class ComponentsCharacteristic extends BaseCharacteristicImpl implements StanderCharacteristicShell<Characteristic>{

	public ComponentsCharacteristic() {
		super();
	}
	
	@Override
	public void initialize(Optional<Characteristic> characteristicArg) {
		characteristic = characteristicArg.get();
	}

}
