package com.stargazerproject.order;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;

public interface TransactionExecute {

	/** @illustrate 开始执行事务, 执行者调用 
	 * 	@param      Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis : 事务运行器接口
	 * **/
	public void transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis);
	
}
