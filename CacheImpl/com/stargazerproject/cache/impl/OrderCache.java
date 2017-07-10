package com.stargazerproject.cache.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.base.impl.TemporaryCacheImpl;
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
@Component(value="orderCache")
@Qualifier("orderCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class OrderCache extends TemporaryCacheImpl<String,Order> implements StanderCharacteristicShell<LoadingCache<String,Order>>{
	
	/** @construction 初始化构造 **/
	public OrderCache() {}

	/**
	* @name 永久缓存Shell推入函数
	* @illustrate 缓存特征置入
	* @param <K> 经过Optional包装的特征
	* **/
	@Override
	public void initialize(Optional<LoadingCache<String, Order>> loadingCacheArg) {
		loadingCache = loadingCacheArg.get();
	}

}