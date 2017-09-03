package com.stargazerproject.cache.impl.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="bigCacheIndexCahceCharacteristic")
@Qualifier("bigCacheIndexCahceCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BigCacheIndexCahceCharacteristic implements Cache<String, Map<String, Integer>>{

	/** @illustrate bigCacheIndexCahce 需要的特征(Map<String, Integer>)接口 **/
	protected Map<String, Map<String, Integer>> map = new ConcurrentSkipListMap<String, Map<String, Integer>>();
	
	@Override
	public void put(Optional<String> key, Optional<Map<String, Integer>> value) {
		map.put(key.get(), value.get());
	}

	@Override
	public Optional<Map<String, Integer>> get(Optional<String> key) {
		return Optional.fromNullable(map.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		map.remove(key.get());
	}

}
