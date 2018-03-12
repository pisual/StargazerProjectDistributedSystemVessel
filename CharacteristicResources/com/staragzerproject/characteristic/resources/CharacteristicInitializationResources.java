package com.staragzerproject.characteristic.resources;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.CharacteristicInitialization;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

public class CharacteristicInitializationResources implements CharacteristicInitialization, BaseCharacteristic<CharacteristicInitialization>{

	private boolean sign = Boolean.FALSE;
	
	@Override
	public Optional<CharacteristicInitialization> characteristic() {
		return null;
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
