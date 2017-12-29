package com.stargazerproject.log.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.Log;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("onlineLogLog")
public class OnlineLogLog extends OnlineLog implements BaseCharacteristic<Log>{
	
	private OnlineLogLog() {}

	@Override
	@Bean(name="onlineLogCharacteristicInitialize")
	@Lazy(true)
	public Optional<Log> characteristic() {
		return Optional.of(this);
	}

}
