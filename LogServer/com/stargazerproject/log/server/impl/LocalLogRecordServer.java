package com.stargazerproject.log.server.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.service.StanderServiceShell;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("localLog")
public class LocalLogRecordServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logRecord")
	private StanderCharacteristicShell<Log> logCharacteristic;
	
	@Autowired
	@Resource(name="localLogCharacteristic")
	private Optional<Log> log;
	
	/** @construction 初始化构造 **/
	private LocalLogRecordServer() {}
	
	@Override
	public void startUp() {
		logCharacteristic.initialize(log);
	}

	@Override
	public void shutDown() {
	}

}
