package com.stargazerproject.sequence.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.base.impl.StandardWorkInServiceState;
import com.stargazerproject.service.baseinterface.WorkInServiceControl;
import com.stargazerproject.service.baseinterface.WorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="standardServerListener")
@Qualifier("standardServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StandardServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("localLogServerListener", "systemParameterCacheServerListener", "byteArrayCacheServerListener", "nodeNegotiateServerListener", "bootInitializationServerListener");
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : standardServerListenerServer Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : standardServerListenerServer Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : standardServerListenerServer Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : standardServerListenerServer Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : standardServerListenerServer Server Failed");
	}
	
}
