package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="serverCache")
@Qualifier("serverCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class ServerCache implements Cache<String, Boolean>{
	
	private Map<String, Boolean> cache = new HashMap<String, Boolean>();
	
	public ServerCache() { }

	@Override
	public void put(Optional<String> key, Optional<Boolean> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<Boolean> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
