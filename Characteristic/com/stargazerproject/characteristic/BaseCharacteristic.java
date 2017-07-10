package com.stargazerproject.characteristic;

import com.google.common.base.Optional;

public interface BaseCharacteristic<E> {
	public Optional<E> characteristic();
}
