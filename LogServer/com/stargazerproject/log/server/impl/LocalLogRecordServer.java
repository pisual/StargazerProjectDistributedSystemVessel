package com.stargazerproject.log.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

@Component(value="localLogRecordServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("localLogRecordServer")
public class LocalLogRecordServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logRecord")
	private StanderCharacteristicShell<Log> logCharacteristic;
	
	@Autowired
	@Qualifier("localLogShell")
	private BaseCharacteristic<Log> localLogShell;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private LocalLogRecordServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LocalLogRecordServer(Optional<StanderCharacteristicShell<Log>> logCharacteristicArg, Optional<BaseCharacteristic<Log>> localLogShellArg) {
		logCharacteristic = logCharacteristicArg.get();
		localLogShell = localLogShellArg.get();
	}
	
	@Override
	public void startUp() {
		logCharacteristic.initialize(localLogShell.characteristic());
	}

	@Override
	public void shutDown() {
	}

}
