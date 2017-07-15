package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public abstract class BaseCacheImpl<K, V> implements Cache<K, V>{
	
	protected Cache<K, V> cache;
	
	protected BaseCacheImpl() {}

	@Override
	public void put(Optional<K> key, Optional<V> value) {
		cache.put(key, value);
	}

	@Override
	public Optional<V> get(Optional<K> key) {
		return cache.get(key);
	}

	@Override
	public void remove(Optional<K> key) {
		cache.remove(key);
	}
	
}