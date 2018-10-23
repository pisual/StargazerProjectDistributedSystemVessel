package com.stargazerproject.cache.impl.resources;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.cache.Cache;

@Component(value="systemParameterCahceCharacteristic")
@Qualifier("systemParameterCahceCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterCahceCharacteristic implements Cache<String, String>{

	private static final long serialVersionUID = -595703056203103395L;
	
	/** @illustrate SystemParameterCache(系统参数缓存)的底层实现依赖并行跳表Map **/
	protected Map<String, String> map = new ConcurrentSkipListMap<String, String>();
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SystemParameterCahceCharacteristic() {}
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <String> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <String> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel put方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public void put(Optional<String> key, Optional<String> value) {
		map.put(key.get(), value.get());
	}

	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param @Optional <String> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <String> Guava包装缓存的Value值，如果Key值没有对应的Value，则返回Optional的空值包装模式
	 * @ThreadSafeMethodsLevel get方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public Optional<String> get(Optional<String> key) {
		return Optional.fromNullable(map.get(key.get()));
	}

	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param @Optional <String> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <String> Guava包装缓存的Boolean值，成功删除返回True，删除失败（没有相应的Key值条数）这返回False
	 * @ThreadSafeMethodsLevel remove方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public Optional<Boolean> remove(Optional<String> key) {
		return (null == map.remove(key.get()))?Optional.of(Boolean.FALSE):Optional.of(Boolean.TRUE);
	}

	/**
	 * @name 清除
	 * @illustrate 清除缓存所有内容
	 * @ThreadSafeMethodsLevel remove方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public void clear(){
		map.clear(); 
	}
	
	/**
	 * @name 获取结果集
	 * @illustrate 获取Set类型的结果集，结果集是有序的（自然排序）
	 * @ThreadSafeMethodsLevel entrySet方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<Set<Entry<String, String>>> entrySet(){
		return ( Optional.of(map.entrySet()) );
	}
	
}
