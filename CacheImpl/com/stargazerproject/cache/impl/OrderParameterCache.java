package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public class OrderParameterCache implements Cache<String, String>{

	private Map<String, String> parameter = new HashMap<String, String>();
	
	@Override
	public void put(Optional<String> key, Optional<String> value) {
		parameter.put(key.get(), value.get());
	}

	@Override
	public Optional<String> get(Optional<String> key) {
		return Optional.fromNullable(parameter.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		parameter.remove(key.get());
	}

}
