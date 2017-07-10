package com.stargazerproject.model.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.model.order.Result;


/** 
 *  @name 结果
 *  @illustrate 基于永久缓存实现
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@SuppressWarnings("unused")
public class ResultVoid implements Result{
	
	/** @illustrate 是否执行完成**/
	private Boolean isComplete;

	public ResultVoid(Optional<Boolean> isCompleteArg) {
		isComplete = isCompleteArg.get();
	}
}
