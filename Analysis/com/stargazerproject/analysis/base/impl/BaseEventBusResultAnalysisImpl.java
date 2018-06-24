package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventBusResultAnalysisExtend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultState;

public class BaseEventBusResultAnalysisImpl implements EventBusResultAnalysisExtend{

	protected EventBusResultAnalysisExtend eventBusResultAnalysisExtend;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> executionResultCache) {
		return eventBusResultAnalysisExtend.analysis(executionResultCache);
	}

	@Override
	public Optional<ResultState> resultState() {
		return eventBusResultAnalysisExtend.resultState();
	}

}
