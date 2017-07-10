package com.stargazerproject.cache.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.base.Optional;
import com.stargazerproject.cache.base.impl.PermanentCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

/** 
 *  @name 全局系统参数
 *  @illustrate 基于永久缓存实现
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
//@Component
@Qualifier("eventCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class EventCache extends PermanentCacheImpl<String,String> implements StanderCharacteristicShell<Map<String,String>>{
	
	/** @construction 初始化构造 **/
	public EventCache() {}

	@Override
	public void initialize(Optional<Map<String, String>> mapArg) {
		map = mapArg.get();
	}
}