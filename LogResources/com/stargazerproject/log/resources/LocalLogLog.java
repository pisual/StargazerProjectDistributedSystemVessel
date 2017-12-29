package com.stargazerproject.log.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.Log;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("localLogLog")
public class LocalLogLog extends LocalLog implements BaseCharacteristic<Log>{
	
	private LocalLogLog() {}

	@Override
	@Bean(name="localLogCharacteristicInitialize")
	public Optional<Log> characteristic() {
		return Optional.of(this);
	}

}
