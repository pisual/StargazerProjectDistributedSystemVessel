package com.stargazerproject.zookeeper.model;

import com.stargazerproject.zookeeper.listener.StrategyCellsConnectionStateListener;


public class ZookeeeperConfiguration {
	
	/**连接字符串 
	 * 例如：10.0.1.13:2181**/
	private String connectString;
	
	/**会话超时事件**/
	private Integer sessionTimeout;

	/**超时连接重试次数**/
	private int retryConnectNumber;
	
	/****/
	private StrategyCellsConnectionStateListener strategyCellsConnectionStateListener;
	
	public ZookeeeperConfiguration(String connectString, Integer sessionTimeout, int retryConnectNumber, StrategyCellsConnectionStateListener strategyCellsConnectionStateListener) {
	this.connectString = connectString;
	this.sessionTimeout = sessionTimeout;
	this.retryConnectNumber = retryConnectNumber;
	this.strategyCellsConnectionStateListener = strategyCellsConnectionStateListener;
	}

	public String getConnectString() {
		return connectString;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public int getRetryConnectNumber() {
		return retryConnectNumber;
	}

	public StrategyCellsConnectionStateListener getStrategyCellsConnectionStateListener() {
		return strategyCellsConnectionStateListener;
	}
}
