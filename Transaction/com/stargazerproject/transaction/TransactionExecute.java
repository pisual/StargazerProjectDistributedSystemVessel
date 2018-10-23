package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.transaction.exception.TransactionException;

/** 
 *  @name 事务运行（TransactionExecute）接口
 *  @illustrate 事务的运行分析基础方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionExecute {

	/** @illustrate 开始执行事务, 执行者调用 
	 * 	@param      Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis : 事务运行器接口，不允许空值
	 *  @ThreadSafeMethodsLevel transactionExecute的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis) throws TransactionException;
	
	/** @illustrate 跳过此事务，通过调用其名下的Event的skipEvent方法来主动放弃Event的执行
	 *  @ThreadSafeMethodsLevel skipTransaction的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void skipTransaction();
}
