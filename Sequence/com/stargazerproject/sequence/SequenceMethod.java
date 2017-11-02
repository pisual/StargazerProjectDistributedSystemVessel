package com.stargazerproject.sequence;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface SequenceMethod {
	public Boolean method();
	public void waitMethod();
	public void setAggregateRootCache(Optional<Cache<String,String>> cache);
	public Optional<Boolean> getWaitMethod();
}
