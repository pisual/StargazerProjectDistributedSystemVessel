package com.stargazerproject.order;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;

/** 
 *  @name 事件初始化（EventAssemble）接口
 *  @illustrate 事件的初始化基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionAssemble {
	
	/** @illustrate 事务生产，生产者调用
	 *  @param      Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis : 事务生产分析器接口
	 * **/
	public void transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis);
	
}
