package com.stargazerproject.analysis; 

import com.google.common.base.Optional;
import com.stargazerproject.order.ResultState;

public interface EventResultAnalysis extends ResultAnalysis{
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();
	
}
