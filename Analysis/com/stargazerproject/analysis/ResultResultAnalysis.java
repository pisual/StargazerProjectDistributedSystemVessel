package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.ResultState;

public interface ResultResultAnalysis<R> {

	/** @illustrate 获取结果状态 
	 * **/
	public Optional<ResultState> resultState();
	
	/** @illustrate 结果分析器 **/
	public Optional<Boolean> analysis(Optional<R> resultCache);
	
}
