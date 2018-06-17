package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 事务初始化（TransactionAssemble）接口
 *  @illustrate 事务的初始化基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionAssemble {
	
	/** @illustrate 事务生产，生产者调用
	 *  @param      Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis : 事务生产分析器接口，不允许空值
	 *  	@ThreadSafeMethodsLevel transactionAssemble的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis);
	
}
