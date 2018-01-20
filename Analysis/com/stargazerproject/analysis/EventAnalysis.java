package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface EventAnalysis {
	public Optional<Boolean> analysis(Optional<Cache<String, String>> parameter);
}
