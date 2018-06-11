package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventAssembleAnalysisExtend;
import com.stargazerproject.cache.Cache;

public class BaseEventAssembleAnalysisImpl implements EventAssembleAnalysisExtend{
	
	protected EventAssembleAnalysisExtend eventAssembleAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache) {
		return eventAssembleAnalysisExtend.analysis(interactionCache);
	}

	@Override
	public void injectEventParameter(Optional<String> Key, Optional<String> value) {
		eventAssembleAnalysisExtend.injectEventParameter(Key, value);
	}

	@Override
	public void injectEventName(Optional<String> value) {
		eventAssembleAnalysisExtend.injectEventName(value);
	}

}
