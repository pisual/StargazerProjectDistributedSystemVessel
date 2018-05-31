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
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BigCacheIndexCahceCharacteristic() {}
	
	@Override
	public void put(Optional<String> key, Optional<Map<String, Integer>> value) {
		map.put(key.get(), value.get());
	}

	/**
	* @name Get方法
	* @illustrate 如果Key值没有对应的Value，则返回Optional的Null对象形式，可以通过isPresent()来进行检测
	* @param Optional<String> key值
	* @param Optional<Map<String, Integer>> Value值
	* **/
	@Override
	public Optional<Map<String, Integer>> get(Optional<String> key) {
		return Optional.fromNullable(map.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		map.remove(key.get());
	}

}
