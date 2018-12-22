package com.staragzerproject.characteristics.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristics.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="componentsCharacteristicShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("componentsCharacteristicShell")
public class ComponentsCharacteristicShell implements Characteristic, BaseCharacteristic<Characteristic>{

	private boolean sign = Boolean.FALSE;
	
	@Override
	public Optional<Characteristic> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<Boolean> singleInitializationStata() {
		return Optional.of(sign);
	}

	@Override
	public void singleInitializationComplete() {
		sign = Boolean.TRUE;
	}

}
