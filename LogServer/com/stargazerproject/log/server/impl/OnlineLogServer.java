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

@Component(value="onlineLogServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("onlineLogServer")
public class OnlineLogServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logRecord")
	private StanderCharacteristicShell<Log> logCharacteristic;
	
	@Autowired
	@Qualifier("onlineLogShell")
	private BaseCharacteristic<Log> logShell;
	
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
	private OnlineLogServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OnlineLogServer(Optional<StanderCharacteristicShell<Log>> logCharacteristicArg, Optional<BaseCharacteristic<Log>> logShellArg) {
		logCharacteristic = logCharacteristicArg.get();
		logShell =logShellArg.get();
	}
	
	@Override
	public void startUp() {
		logCharacteristic.initialize(logShell.characteristic());
	}

	@Override
	public void shutDown() {
		logCharacteristic.initialize(localLogShell.characteristic());
	}

}
