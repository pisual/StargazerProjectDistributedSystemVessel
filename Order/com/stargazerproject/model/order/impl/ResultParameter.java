package com.stargazerproject.model.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.model.order.Result;


/** 结果 **/
@SuppressWarnings("unused")
public final class ResultParameter extends ResultVoid implements Result{

	/** @illustrate 事件结果纪录接口,需要注入baseMapUnit **/
	private Cache<String, String> executionResult;

	public ResultParameter(Optional<Boolean> isCompleteArg, Optional<Cache<String, String>> executionResultArg) {
		super(isCompleteArg);
		executionResult = executionResultArg.get();
	}
}