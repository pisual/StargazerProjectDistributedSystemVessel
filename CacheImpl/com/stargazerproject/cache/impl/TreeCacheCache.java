package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.curator.framework.recipes.cache.TreeCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="treeCacheCache")
@Qualifier("treeCacheCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class TreeCacheCache implements Cache<String, TreeCache>{
	
	private Map<String, TreeCache> cache = new HashMap<String, TreeCache>();
	
	public TreeCacheCache() { }

	@Override
	public void put(Optional<String> key, Optional<TreeCache> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<TreeCache> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
