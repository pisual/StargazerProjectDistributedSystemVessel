package com.stargazer.segmentation;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;


public interface Execute {
	public Boolean executeEvent(Optional<Cache<String, String>> parameter, Optional<Result> result);
}
