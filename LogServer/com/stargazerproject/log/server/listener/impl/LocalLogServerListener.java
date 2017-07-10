package com.stargazerproject.log.server.listener.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.WorkInServiceControl;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.service.impl.StandardWorkInServiceState;

@Component(value="localLogServerListener")
@Qualifier("localLogServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LocalLogServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	/** @illustrate 启动服务 **/
	@Override
	public void starting() {
		Logger.getLogger(this.getClass()).info("LocalLog Server Starting");
	}
	
	/** @illustrate 服务正在运行 **/
	@Override
	public void running() {
		super.running();
		Logger.getLogger(this.getClass()).info("LocalLog Server Running");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		Logger.getLogger(this.getClass()).info("LocalLog Server Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		Logger.getLogger(this.getClass()).info("LocalLog Server Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		Logger.getLogger(this.getClass()).info("LocalLog Server Failed");
	}
	
}
