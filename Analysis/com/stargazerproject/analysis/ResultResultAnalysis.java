package com.stargazerproject.analysis; 

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface ResultResultAnalysis{
	
	/** @illustrate 结果分析器 **/
	public Optional<Boolean> analysis(Optional<Cache<String, String>> executionResultCache);
	
}
