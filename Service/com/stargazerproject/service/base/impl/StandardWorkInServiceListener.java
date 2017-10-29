package com.stargazerproject.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.service.baseinterface.WorkInServiceControl;

/** 
 *  @name 标准服务控制
 *  @illustrate 包含了基础的服务控制，通过调用serverStartserverStop或serverStop来改变服务状态
 *  @author Felixerio
 *  **/
public class StandardWorkInServiceListener extends Listener implements WorkInServiceControl{
	
	/** @illustrate 服务状态变量 **/
	protected Boolean serverState = Boolean.FALSE;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod baseLog;
	
	/** @construction 初始化构造 **/
	public StandardWorkInServiceListener() {}
	
	/** @illustrate 启动服务 **/
	@Override
	public void starting() {
	}
	
	/** @illustrate 服务正在运行 **/
	@Override
	public void running() {
		serverState = Boolean.TRUE;
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		serverState = Boolean.FALSE;
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		serverState = Boolean.FALSE;
	}
	
}