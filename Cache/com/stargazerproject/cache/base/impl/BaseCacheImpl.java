package com.stargazerproject.cache.base.impl;

import java.util.Set;
import java.util.Map.Entry;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public abstract class BaseCacheImpl<K, V> implements Cache<K, V>{
	
	private static final long serialVersionUID = -8433617516060406881L;
	
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
	public Optional<Boolean> remove(Optional<K> key) {
		return cache.remove(key);
	}
	
	@Override
	public void clear() {
		cache.clear();
	}
	
	@Override
	public Optional<Set<Entry<K, V>>> entrySet() {
		return ( cache.entrySet() );
	}

}