package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="serverListCache")
@Qualifier("serverListCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class ServerListCache implements Cache<String, List<String>>{
	
	private Map<String, List<String>> cache = new HashMap<String, List<String>>();
	
	public ServerListCache() { }

	@Override
	public void put(Optional<String> key, Optional<List<String>> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<List<String>> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
