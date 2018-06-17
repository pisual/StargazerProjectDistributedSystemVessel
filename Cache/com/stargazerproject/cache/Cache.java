package com.stargazerproject.cache;

import java.io.Serializable;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public interface Cache<K, V> extends Serializable{
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <V> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public void put(Optional<K> key, Optional<V> value);
	
	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <V> Guava包装缓存的Value值，如果Key值没有对应的Value，则返回Optional的空值包装模式
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public Optional<V> get(Optional<K> key);
	
	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <V> Guava包装缓存的Boolean值，成功删除返回True，删除失败（没有相应的Key值条数）这返回False
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public Optional<Boolean> remove(Optional<K> key);
}
