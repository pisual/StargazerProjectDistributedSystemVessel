package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;

/** 
 *  @name 大型数据临时缓存
 *  @Shell LoadingCache<K, V> loadingCache，Guava LoadingCache 类型的通用接口
 *  @illustrate 在指定条件下发生解出操作的缓存，
 *              1.自主解出
 *              2.超时间解出
 *              3.超容量解出
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public abstract class TemporaryBigMemoryCacheImpl<K, V> implements BigCache<K, V>{
	
	/** @illustrate org.ehcache.Cache 缓存接口 **/
	protected BigCache<K, V> cache;
	
	/** @construction 初始化构造 **/
	public TemporaryBigMemoryCacheImpl() {}

	@Override
	public void put(Optional<K> key, V value) {
		cache.put(key, value);
	}

	@Override
	public V get(Optional<K> key) {
		return cache.get(key);
	}

	@Override
	public void remove(Optional<K> key) {
		cache.remove(key);
	}

}