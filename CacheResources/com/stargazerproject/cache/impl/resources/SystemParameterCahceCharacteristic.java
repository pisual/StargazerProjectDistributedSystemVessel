package com.stargazerproject.cache.impl.resources;

import java.util.Map;
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

	/** @illustrate SystemParameterCache(系统参数缓存)需要的特征(Map<String, String>)接口 **/
	protected Map<String, String> map = new ConcurrentSkipListMap<String, String>();
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SystemParameterCahceCharacteristic() {}
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入
	 * @param @Optional <String> Guava包装缓存的Key值
	 * @param @Optional <String> Guava包装缓存的Value值
	 * @ThreadSafeMethodsLevel Put方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
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
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public Optional<String> get(Optional<String> key) {
		return Optional.fromNullable(map.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		map.remove(key.get());
	}

}
