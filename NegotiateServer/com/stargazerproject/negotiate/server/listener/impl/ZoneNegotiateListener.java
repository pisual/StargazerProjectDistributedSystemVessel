package com.stargazerproject.negotiate.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.base.impl.StandardWorkInServiceListener;

@Component(value="zoneNegotiateListener")
@Qualifier("zoneNegotiateListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ZoneNegotiateListener extends StandardWorkInServiceListener{
	
	@Override
	public void starting() {
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : zoneNegotiateListener Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : zoneNegotiateListener Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : zoneNegotiateListener Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : zoneNegotiateListener Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : zoneNegotiateListener Server Failed");
	}
	
}
