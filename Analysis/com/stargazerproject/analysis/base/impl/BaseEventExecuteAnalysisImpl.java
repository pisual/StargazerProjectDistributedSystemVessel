package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultRecord;

public abstract class BaseEventExecuteAnalysisImpl implements EventExecuteAnalysis {
	
	protected EventExecuteAnalysis eventExecuteAnalysis;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache, Optional<ResultRecord> result) {
		return eventExecuteAnalysis.analysis(interactionCache, result);
	}
}
