package com.stargazerproject.queue.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.base.impl.StandardWorkInServiceState;
import com.stargazerproject.service.baseinterface.WorkInServiceControl;
import com.stargazerproject.service.baseinterface.WorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="logQueueServerListener")
@Qualifier("logQueueServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogQueueServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LogQueueServerListener() {
		super();
	}
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LogQueue Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LogQueue Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LogQueue Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LogQueue Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LogQueue Server Failed");
	}
	
}
