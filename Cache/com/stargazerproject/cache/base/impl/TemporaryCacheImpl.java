package com.stargazerproject.cache.base.impl;

import java.util.concurrent.ExecutionException;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.Cache;

/** 
 *  @name 临时缓存
 *  @Shell LoadingCache<K, V> loadingCache，Guava LoadingCache 类型的通用接口
 *  @illustrate 在指定条件下发生解出操作的缓存，
 *              1.自主解出
 *              2.超时间解出
 *              3.超容量解出
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public abstract class TemporaryCacheImpl<K, V> implements Cache<K, V>{
	
	/** @illustrate 通用LoadingCache Guava 缓存接口 **/
	protected LoadingCache<K, V> loadingCache;
	
	/** @construction 初始化构造 **/
	public TemporaryCacheImpl() {}
	
	@Override
	public void put(Optional<K> key ,Optional<V> value) {
		loadingCache.put(key.get(), value.get());
	}

	@Override
	public Optional<V> get(Optional<K> key){
		try {
			return Optional.of(loadingCache.get(key.get()));
		} catch (ExecutionException e) {
			throw new NullPointerException("Key :"+key.get()+" 的Value不存在");
		}
	}

	@Override
	public void remove(Optional<K> key){
		loadingCache.invalidate(key.get());
	}
}
