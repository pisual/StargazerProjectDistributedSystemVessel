package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventAssembleAnalysisImpl implements EventAssembleAnalysis {
	
	protected EventAssembleAnalysis eventAssembleAnalysis;
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache) {
		return eventAssembleAnalysis.analysis(interactionCache);
	}

	@Override
	public void injectEventParameter(Optional<String> Key, Optional<String> value) {
		eventAssembleAnalysis.injectEventParameter(Key, value);
	}

}
