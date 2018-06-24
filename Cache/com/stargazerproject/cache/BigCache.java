package com.stargazerproject.cache;

import java.io.Serializable;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 大型数据缓存接口
 *  @illustrate 实现大型数据缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public interface BigCache<K, V> extends Serializable{
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <V> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public void put(Optional<K> key, V value);
	
	/**
	 * @name 添加（分片添加）
	 * @illustrate 如果需要置入数据的Key已经存在，则自动进行分片存贮
	 * @param  @Optional <K> 缓存的Key值, 不允许空值
	 * @param  @Optional <V> 缓存的Value值, 允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public void add(Optional<K> key, V value);
	
	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param @Optional <K> 缓存的Key值，不允许空值
	 * @return @Optional <V> 缓存的Value值，允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public Optional<V> get(Optional<K> key);
	
	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param @Optional <K> 缓存的Key值，不允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public void remove(Optional<K> key);

}
