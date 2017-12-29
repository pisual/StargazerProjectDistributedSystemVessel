package com.stargazerproject.interfaces.characteristic.shell;

import com.google.common.base.Optional;

public interface BaseCharacteristic<E> {
	public Optional<E> characteristic();
}
