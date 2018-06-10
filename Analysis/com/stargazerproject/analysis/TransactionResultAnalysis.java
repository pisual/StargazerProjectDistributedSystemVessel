package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.ResultState;

public interface TransactionResultAnalysis {
	
	/** @illustrate 结果分析器 **/
	public Optional<Boolean> analysis(Optional<List<EventResult>> events);
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();
	
}
