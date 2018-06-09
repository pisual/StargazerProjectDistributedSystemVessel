package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventExecuteAnalysisExtend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.ResultRecord;

public class BaseEventExecuteAnalysis implements EventExecuteAnalysisExtend{

	protected EventExecuteAnalysisExtend eventExecuteAnalysisExtend;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache, Optional<ResultRecord> result) {
		return eventExecuteAnalysisExtend.analysis(interactionCache, result);
	}
}
