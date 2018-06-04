package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.ResultState;

public interface TransactionResultAnalysis {
	
	/** @illustrate 结果分析器 **/
	public Optional<Boolean> analysis(Optional<Event[]> events);
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();
	
}
