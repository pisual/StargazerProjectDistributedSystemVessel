package com.stargazerproject.analysis.extend;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.transaction.ResultState;

public interface EventResultAnalysisExtend extends EventResultAnalysis{
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();

}
