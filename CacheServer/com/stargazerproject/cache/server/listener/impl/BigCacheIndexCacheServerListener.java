package com.stargazerproject.cache.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.WorkInServiceControl;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.service.impl.StandardWorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="bigCacheIndexCacheServerListener")
@Qualifier("bigCacheIndexCacheServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BigCacheIndexCacheServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("localLogServerListener");
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : bigCacheIndexCache Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : bigCacheIndexCache Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : bigCacheIndexCache Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : bigCacheIndexCache Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : bigCacheIndexCache Server Failed");
	}
	
}
