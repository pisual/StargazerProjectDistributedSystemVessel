package com.stargazerproject.analysis.extend;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.bus.BusEventTimeoutModel;
import com.stargazerproject.transaction.ResultState;

public interface EventBusResultAnalysisExtend extends EventResultAnalysis{
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();

	/** @illustrate 获取Event超时控制参数 **/
	public Optional<BusEventTimeoutModel> busEventTimeout();
}
