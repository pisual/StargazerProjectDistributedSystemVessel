package com.stargazerproject.service.base.impl;

import com.stargazerproject.service.baseinterface.WorkInServiceState;

public class StandardWorkInServiceState extends StandardWorkInServiceListener implements WorkInServiceState{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public StandardWorkInServiceState() {
		super();
	}
	
	/** @illustrate 检测当前服务的状态，如果不是启动（Start）状态 将抛出异常 IllegalStateException
	 *  @exception	IllegalStateException
	 * **/
	@Override
	public void serverstateCheck() {
		if(serverState.equals(Boolean.FALSE)){
			throw new IllegalStateException("Server Not Start");
		}
	}

	@Override
	public boolean getServerstate() {
		return serverState;
	}
}
