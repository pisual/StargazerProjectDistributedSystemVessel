package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="leaderLatchParameterCache")
@Qualifier("leaderLatchParameterCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class LeaderLatchParameterCache implements Cache<String, LeaderLatch>{
	
	private Map<String, LeaderLatch> cache = new HashMap<String, LeaderLatch>();
	
	public LeaderLatchParameterCache() { }

	@Override
	public void put(Optional<String> key, Optional<LeaderLatch> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<LeaderLatch> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
