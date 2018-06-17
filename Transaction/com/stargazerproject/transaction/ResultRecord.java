package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name Result记录接口
 *  @illustrate 记录结果相关的功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface ResultRecord {
	
	/** @illustrate 完成，完成和成功是不同的，完成意味着执行完成，结果有可能成功或失败的，需要指定执行结果 ResultState
	 *  @param	Optional<ResultState> resultState，执行结果，不可以为空值
	 *  @ThreadSafeMethodsLevel complete的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public Optional<ResultRecord> complete(Optional<ResultState> resultState);

	/** @illustrate 记录异常消息 
	 *  @param	Optional<String> errorMessage，错误信息，不可以为空值
	 *  @param  Optional<Exception> exception，异常类信息，可以为空值
	 *  	 *  @ThreadSafeMethodsLevel errorMessage的线程安全级别为ThreadSafeLevel.ThreadSafe，线程安全
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception);

}
