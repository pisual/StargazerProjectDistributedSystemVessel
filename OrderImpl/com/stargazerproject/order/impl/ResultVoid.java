package com.stargazerproject.order.impl;

import com.stargazerproject.order.Result;


/** 
 *  @name 结果
 *  @illustrate 基于永久缓存实现
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public class ResultVoid implements Result{
	
	/** @illustrate 是否执行完成**/
	private Boolean isComplete;

	public ResultVoid() {
		isComplete = Boolean.FALSE;
	}

	@Override
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public void Complete() {
		isComplete = Boolean.TRUE;
	}
	
}
