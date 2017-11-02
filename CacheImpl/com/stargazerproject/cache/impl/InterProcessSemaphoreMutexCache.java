package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="interProcessSemaphoreMutexCache")
@Qualifier("interProcessSemaphoreMutexCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class InterProcessSemaphoreMutexCache implements Cache<String, InterProcessSemaphoreMutex>{
	
	private Map<String, InterProcessSemaphoreMutex> cache = new HashMap<String, InterProcessSemaphoreMutex>();
	
	public InterProcessSemaphoreMutexCache() { }

	@Override
	public void put(Optional<String> key, Optional<InterProcessSemaphoreMutex> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<InterProcessSemaphoreMutex> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
