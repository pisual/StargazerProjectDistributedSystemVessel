package com.stargazerproject.cache.resources.temporarycache.cacheLoader;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.model.order.impl.Order;

@Component
@Qualifier("temporaryCacheOrderCacheCacheLoader")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TemporaryCacheOrderCacheCacheLoader implements BaseCharacteristic<CacheLoader<String, Order>>{

	private CacheLoader<String, Order> cacheLoader;

	private TemporaryCacheOrderCacheCacheLoader() {}
	
	@Override
	@Bean(name="temporaryCacheOrderCacheCacheLoaderCharacteristic")
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