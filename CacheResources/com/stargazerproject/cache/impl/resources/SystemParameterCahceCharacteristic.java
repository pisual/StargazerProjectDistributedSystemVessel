package com.stargazerproject.cache.impl.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

@Component(value="systemParameterCahceCharacteristic")
@Qualifier("systemParameterCahceCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterCahceCharacteristic implements Cache<String, String>{

	/** @illustrate SystemParameterCache(系统参数缓存)需要的特征(Map<String, String>)接口 **/
	protected Map<String, String> map = new ConcurrentSkipListMap<String, String>();
	
	@Override
	public void put(Optional<String> key, Optional<String> value) {
		map.put(key.get(), value.get());
	}

	@Override
	public Optional<String> get(Optional<String> key) {
		return Optional.fromNullable(map.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		map.remove(key.get());
	}

}
