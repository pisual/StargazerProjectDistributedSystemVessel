package com.stargazerproject.cache.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInitialization;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

/** 
 *  @name 事件（Event）结果专用Cache
 *  @illustrate 事件（Event）结果专用Cache，用于初始化事件结果缓存的一些必要内容
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
//@Component
@Qualifier("eventCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@NeedInitialization(content = "")
public final class EventResultCache extends BaseCacheImpl<String,String> implements StanderCharacteristicShell<Cache<String,String>>{
		
	/** @construction 初始化构造 **/
	public EventResultCache() {}
	
	@Override
	public void initialize(Optional<Cache<String, String>> cacheArg) {
		cache = cacheArg.get();
	}

}