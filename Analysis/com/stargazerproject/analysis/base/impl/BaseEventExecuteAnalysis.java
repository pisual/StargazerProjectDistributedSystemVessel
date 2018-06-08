package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;
import com.stargazerproject.cache.Cache;

public class BaseEventAnalysisImpl implements EventAnalysis{

	protected EventAnalysis eventAnalysis;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> parameter) {
		return eventAnalysis.analysis(parameter);
	}
}
