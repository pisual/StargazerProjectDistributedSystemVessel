package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultState;

public abstract class BaseEventResultAnalysisImpl implements EventResultAnalysis {

	protected EventResultAnalysis eventResultAnalysis;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> executionResultCache) {
		return eventResultAnalysis.analysis(executionResultCache);
	}

	@Override
	public Optional<ResultState> resultState() {
		return eventResultAnalysis.resultState();
	}

}
