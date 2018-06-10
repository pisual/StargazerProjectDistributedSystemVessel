package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.ResultResultAnalysis;
import com.stargazerproject.transaction.Result;
import com.stargazerproject.transaction.ResultRecord;
import com.stargazerproject.transaction.ResultState;


/** 
 *  @name 事件结果（BaseEventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseEventResult implements Result{

	private static final long serialVersionUID = -8725503398105907243L;
	
	protected Result result;
	
	protected BaseEventResult() {}
	
	@Override
	public void resultResult(ResultResultAnalysis resultResultAnalysis) {
		result.resultResult(resultResultAnalysis);
	}

	@Override
	public Optional<ResultRecord> complete(Optional<ResultState> resultState) {
		return result.complete(resultState);
	}

	@Override
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception) {
		return result.errorMessage(errorMessage, exception);
	}

	@Override
	public boolean sameValueAs(Result other) {
		return false;
	}

}