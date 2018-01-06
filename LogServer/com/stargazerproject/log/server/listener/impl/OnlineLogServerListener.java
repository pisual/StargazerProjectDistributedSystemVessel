package com.stargazerproject.log.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.service.base.impl.StandardWorkInServiceState;
import com.stargazerproject.service.baseinterface.WorkInServiceControl;
import com.stargazerproject.service.baseinterface.WorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="onlineLogServerListener")
@Qualifier("onlineLogServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OnlineLogServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OnlineLogServerListener() {}
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("localLogServerListener","systemParameterCacheServerListener","logQueueServerListener");
		baseLog.INFO(this, "OnlineLog Server Starting");
	}
	
	@Override
	public void running() {
		baseLog.INFO(this, "OnlineLog Server Run");
	}
	
}
