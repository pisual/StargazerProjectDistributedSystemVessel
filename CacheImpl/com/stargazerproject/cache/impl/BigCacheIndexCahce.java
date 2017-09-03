package com.stargazerproject.cache.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

/** 
 *  @name bigCache Index Cahce
 *  @illustrate 基于永久缓存实现
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component(value="bigCacheIndexCahce")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("bigCacheIndexCahce")
public final class BigCacheIndexCahce extends BaseCacheImpl<String,Map<String, Integer>> implements StanderCharacteristicShell<Cache<String, Map<String, Integer>>>{
	
	/** @construction 初始化构造 **/
	public BigCacheIndexCahce() {}

	@Override
	public void initialize(Optional<Cache<String, Map<String, Integer>>> cacheArg) {
		cache = cacheArg.get();
	}
}