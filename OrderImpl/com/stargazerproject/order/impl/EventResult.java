package com.stargazerproject.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;
import com.stargazerproject.order.ResultState;


/** 
 *  @name 事件结果实现
 *  @illustrate 事件（Event）的结果实现
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public final class EventResult implements Result{
	
	/** @illustrate 事件结果标志，初始状态为FAULT（未完成） **/
	private ResultState resultState = ResultState.FAULT;
	
	/** @illustrate **/
	private Cache<String, String> executionResult;
	
	public EventResult(Optional<Cache<String, String>> executionResultArg) {
		executionResult = executionResultArg.get();
	}
	
	@Override
	public void complete() {
		resultState = ResultState.SUCCESS;
	}

	@Override
	public Optional<ResultState> resultState() {
		return Optional.of(resultState);
	}

}