package com.stargazerproject.analysis.extend;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.transaction.ResultState;

public interface TransactionResultAnalysisExtend extends TransactionResultAnalysis{

	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();
	
}
