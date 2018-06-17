package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 事务结果（TransactionResult）接口
 *  @illustrate 事务的结果分析基础方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionResult {

	/** @illustrate 分析事务结果，分析者调用
	 *  @param      Optional<TransactionResultAnalysis> transactionResultAnalysis : 事务结果分析器接口，不允许空值
	 *  @ThreadSafeMethodsLevel transactionResult的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysis);
	
}
