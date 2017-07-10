package com.stargazerproject.service.impl;

import com.stargazerproject.service.WorkInServiceState;

public class StandardWorkInServiceState extends StandardWorkInServiceListener implements WorkInServiceState{
	
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
