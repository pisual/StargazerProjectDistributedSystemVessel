package com.stargazerproject.transaction;

import com.google.common.base.Optional;

/** 
 *  @name Result记录接口
 *  @illustrate 记录结果相关的功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface ResultRecord {
	
	/** @illustrate 记录结果 **/
	public Optional<ResultRecord> complete(Optional<ResultState> resultState);

	/** @illustrate 记录异常消息 **/
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception);

}
