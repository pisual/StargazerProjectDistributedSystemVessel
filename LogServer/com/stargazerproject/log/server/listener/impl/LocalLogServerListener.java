package com.stargazerproject.log.server.listener.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.base.impl.StandardWorkInServiceState;
import com.stargazerproject.service.baseinterface.WorkInServiceControl;
import com.stargazerproject.service.baseinterface.WorkInServiceState;

@Component(value="localLogServerListener")
@Qualifier("localLogServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LocalLogServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LocalLogServerListener() {}
	
	/** @illustrate 启动服务 **/
	@Override
	public void starting() {
		Logger.getLogger(this.getClass()).info("Stargazer ServiceControlSystem Report : LocalLog Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LocalLog Server Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LocalLog Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LocalLog Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : LocalLog Server Failed");
	}
	
}
