package com.stargazerproject.service.baseinterface;

import com.google.common.util.concurrent.Service.State;

/** 
 *  @name 服务控制
 *  @illustrate 服务控制的基础方法
 *  @author Felixerio
 *  **/
public interface WorkInServiceControl {
	
	/** @illustrate 启动服务 **/
	public void starting();
	
	/** @illustrate 服务正在运行 **/
	public void running();
	
	/** @illustrate 开始停止服务 **/
	public void stopping(State from);
	
	/** @illustrate 服务停止 **/
	public void terminated(State from);
	
	/** @illustrate 服务失败 **/
	public void failed(State from, Throwable failure);
}