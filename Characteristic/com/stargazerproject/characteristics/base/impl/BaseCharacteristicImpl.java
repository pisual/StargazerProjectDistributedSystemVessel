package com.stargazerproject.characteristic.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.Characteristic;

public class BaseCharacteristicImpl implements Characteristic{

	protected Characteristic characteristic;
	
	@Override
	public Optional<Boolean> singleInitializationStata() {
		return characteristic.singleInitializationStata();
	}

	@Override
	public void singleInitializationComplete() {
		characteristic.singleInitializationComplete();
	}

}
