package com.stargazerproject.log.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("onlineLog")
public class OnlineLogRecordServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logRecord")
	private StanderCharacteristicShell<Log> logCharacteristic;
	
	/** @construction 初始化构造 **/
	private OnlineLogRecordServer() {}
	
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener","systemParameterCacheServerListener","logQueueServerListener");
		Optional<Log> log = BeanContainer.instance().getBean(Optional.of("onlineLogCharacteristicInitialize"), Optional.class);
		logCharacteristic.initialize(log);
	}

	@Override
	public void shutDown() {
	}

}
