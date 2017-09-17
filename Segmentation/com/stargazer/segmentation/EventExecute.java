package com.stargazer.segmentation;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;


public interface EventExecute {
	public Boolean executeEvent(Optional<Cache<String, String>> parameter);
}
