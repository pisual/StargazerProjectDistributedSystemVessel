package com.stargazerproject.cache.server.listener.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.WorkInServiceControl;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.service.impl.StandardWorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="systemParameterCacheServerListener")
@Qualifier("systemParameterCacheServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterCacheServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("localLogServerListener");
		baseLog.INFO(this, "SystemParameterCache Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "SystemParameterCache Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "SystemParameterCache Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "SystemParameterCache Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "SystemParameterCache Server Failed");
	}
	
}
