package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.ResultResultAnalysisExtend;
import com.stargazerproject.cache.Cache;

public class BaseResultResultAnalysisImpl implements ResultResultAnalysisExtend{
	
	private ResultResultAnalysisExtend resultResultAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> executionResultCache) {
		return resultResultAnalysisExtend.analysis(executionResultCache);
	}

}
