package com.stargazerproject.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.base.Optional;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public interface BigCache<K, V> {
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入
	 * @param <K> 缓存的Key值
	 * @param <V> 缓存的Value值
	 * @Optional Guava包装
	 * **/
	public void put(Optional<K> key, V value);
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入
	 * @param <K> 缓存的Key值
	 * @param <V> 缓存的Value值
	 * @Optional Guava包装
	 * **/
	public void add(Optional<K> key, V value);
	
	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param <K> 缓存的Key值
	 * @return <V> 缓存的Value值
	 * @exception ExecutionException
	 * @Optional Guava包装
	 * **/
	public V get(Optional<K> key);
	
	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param <K> 缓存的Key值
	 * @Optional Guava包装
	 * **/
	public void remove(Optional<K> key);
}
