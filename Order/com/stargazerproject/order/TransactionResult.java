package com.stargazerproject.order;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;

public interface TransactionResult {

	/** @illustrate 分析事务结果，分析者调用
	 *  @param      Optional<TransactionResultAnalysis> transactionResultAnalysis : 事务结果分析器接口
	 * **/
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysis);
	
}
