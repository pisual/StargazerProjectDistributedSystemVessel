package com.stargazerproject.cache.base.impl;

import java.util.Map;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

/** 
 *  @name 永久缓存
 *  @Shell Map<K,V> map，Map类型的通用接口
 *  @illustrate 一旦创建Map-Value映射缓存，就会在虚拟机存活期间一直存在，可以进行覆盖(更新)操作
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public abstract class PermanentCacheImpl<K, V> implements Cache<K, V>{
	
	/** @illustrate 通用Map接口 **/
	protected Map<K,V> map;
	
	/** @construction 初始化构造 **/
	public PermanentCacheImpl() {}

	@Override
	public void put(Optional<K> key, Optional<V> value) {
		map.put(key.get(), value.get());
	}

	@Override
	public Optional<V> get(Optional<K> key) {
		return Optional.of(map.get(key.get()));
	}

	@Override
	public void remove(Optional<K> key) {
		map.remove(key.get());
	}
}