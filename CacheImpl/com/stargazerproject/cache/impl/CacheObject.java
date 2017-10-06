package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public abstract class CacheObject<K, V> implements Cache<K, V>{
	
	private Map<K, V> cache;
	
	public CacheObject() {
		cache = new HashMap<K, V>();
	}

	@Override
	public void put(Optional<K> key, Optional<V> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<V> get(Optional<K> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<K> key) {
		cache.remove(key.get());
	}

}
