package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="objectParameterCache")
@Qualifier("objectParameterCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class ObjectParameterCache implements Cache<String, String>{
	
	private Map<String, String> cache = new HashMap<String, String>();
	
	public ObjectParameterCache() { }

	@Override
	public void put(Optional<String> key, Optional<String> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<String> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
