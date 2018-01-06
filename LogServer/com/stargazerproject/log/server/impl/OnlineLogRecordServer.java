package com.stargazerproject.log.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="onlineLogRecordServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("onlineLogRecordServer")
public class OnlineLogRecordServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logRecord")
	private StanderCharacteristicShell<Log> logCharacteristic;
	
	@Autowired
	@Qualifier("onlineLogShell")
	private BaseCharacteristic<Log> localLogShell;
	
	/** @construction 初始化构造 **/
	private OnlineLogRecordServer() {}
	
	@Override
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener","systemParameterCacheServerListener","logQueueServerListener");
		logCharacteristic.initialize(localLogShell.characteristic());
	}

	@Override
	public void shutDown() {
	}

}
