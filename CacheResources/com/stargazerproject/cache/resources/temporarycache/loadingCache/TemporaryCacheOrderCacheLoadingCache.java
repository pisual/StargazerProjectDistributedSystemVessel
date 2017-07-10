package com.stargazerproject.cache.resources.temporarycache.loadingCache;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("temporaryCacheOrderCacheLoadingCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TemporaryCacheOrderCacheLoadingCache implements BaseCharacteristic<LoadingCache<String,Order>>{

	/**
	* @name LoadingCache
	* @illustrate 缓存特征
	* **/
	private Cache<String,String> systemParameter;
	
	/**
	* @name LoadingCache
	* @illustrate 缓存特征
	* **/
	@Resource(name="temporaryCacheOrderCacheCacheLoaderCharacteristic")
	private Optional<CacheLoader<String, Order>> cacheLoader;
	
	
	/**
	* @name LoadingCache
	* @illustrate 缓存特征
	* **/
	@Resource(name="temporaryCacheOrderCacheRemovalListenerCharacteristic")
	private Optional<RemovalListener<String, Order>> removalListener;
	
	/**
	* @name LoadingCache
	* @illustrate 缓存特征
	* **/
	private LoadingCache<String,Order> loadingCache;
	
	private TemporaryCacheOrderCacheLoadingCache() {}
	
	@Override
	@Bean(name="temporaryCacheOrderCacheLoadingCacheCharacteristic")
	@Lazy(true)
	@SuppressWarnings("unchecked")
	public Optional<LoadingCache<String, Order>> characteristic() {
		systemParameter = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		loadingCacheBuilder();
		return Optional.of(loadingCache);
	}
	
	
	private void loadingCacheBuilder(){
	     loadingCache = CacheBuilder
					   .newBuilder()
					   .concurrencyLevel(getIntegerParameter("OrderCache_concurrencyLevel"))
					   .expireAfterWrite(getIntegerParameter("OrderCache_expireAfterWriteTime"),TimeUnit.MILLISECONDS)
					   .initialCapacity(getIntegerParameter("OrderCache_initialSize"))
					   .maximumSize(getIntegerParameter("OrderCache_maxSize"))
					   .expireAfterAccess(getIntegerParameter("OrderCache_expireAfterReadTime"),TimeUnit.MILLISECONDS)
					   .removalListener(removalListener.get())
					   .build(cacheLoader.get());
	     }
	
	private Integer getIntegerParameter(String key){
		return Integer.parseInt(systemParameter.get(Optional.of(key)).get());}
}