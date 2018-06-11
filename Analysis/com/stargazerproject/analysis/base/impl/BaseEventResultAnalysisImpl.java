package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventResultAnalysisExtend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultState;

public class BaseEventResultAnalysisImpl implements EventResultAnalysisExtend{

	protected EventResultAnalysisExtend eventResultAnalysisExtend;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> executionResultCache) {
		return eventResultAnalysisExtend.analysis(executionResultCache);
	}

	@Override
	public Optional<ResultState> resultState() {
		return eventResultAnalysisExtend.resultState();
	}

}
