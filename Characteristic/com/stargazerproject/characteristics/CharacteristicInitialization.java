package com.stargazerproject.characteristics;

import com.google.common.base.Optional;

public interface CharacteristicInitialization {
	
	public Optional<Boolean> singleInitializationStata();
	
	public void singleInitializationComplete();
}
