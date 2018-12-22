package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusEventTimeoutModel;
import com.stargazerproject.transaction.EventResult;

public interface EventBusResultAnalysis extends ResultResultAnalysis<List<EventResult>>{

	/** @illustrate 获取Event超时控制参数 **/
	public Optional<BusEventTimeoutModel> busEventTimeout();
	
}
