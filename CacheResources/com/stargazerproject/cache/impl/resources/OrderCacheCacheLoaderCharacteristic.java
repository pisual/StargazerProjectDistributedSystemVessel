package com.stargazerproject.cache.impl.resources;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.impl.Order;

@Component(value="orderCacheCacheLoaderCharacteristic")
@Qualifier("orderCacheCacheLoaderCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCacheCacheLoaderCharacteristic implements BaseCharacteristic<CacheLoader<String, Order>>{

	private CacheLoader<String, Order> cacheLoader;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderCacheCacheLoaderCharacteristic() {}
	
	@Override
	public Optional<CacheLoader<String, Order>> characteristic() {
		initializationCacheLoader();
		return Optional.of(cacheLoader);
	}
	
	
	private void initializationCacheLoader(){
		cacheLoader = new CacheLoader<String, Order>(){
			@Override
			public Order load(String key) throws ExecutionException {
				throw new ExecutionException("Order Cache Key Inexistence",new Throwable());
			}
		};
	}
	
}