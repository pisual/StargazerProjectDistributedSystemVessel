package com.stargazerproject.cache.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.model.order.impl.Order;

/** 
 *  @name Order(指令事务)缓存
 *  @Shell Map<K,V> parameters，Map类型的通用接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
//@Component(value="orderCache")
@Qualifier("orderCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class OrderCache extends BaseCacheImpl<String,Order> implements StanderCharacteristicShell<Cache<String,Order>>{
	
	/** @construction 初始化构造 **/
	public OrderCache() {}

	@Override
	public void initialize(Optional<Cache<String, Order>> cacaheArg) {
		cache = cacaheArg.get();
	}

}