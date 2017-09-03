package com.stargazerproject.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;


/** 结果 **/
@SuppressWarnings("unused")
public final class ResultParameter extends ResultVoid implements Result{

	/** @illustrate 事件结果纪录接口, **/
	private Cache<String, String> executionResult;

	public ResultParameter(Optional<Cache<String, String>> executionResultArg) {
		super();
		executionResult = executionResultArg.get();
	}
}