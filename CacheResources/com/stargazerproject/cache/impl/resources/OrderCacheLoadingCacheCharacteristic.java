package com.stargazerproject.cache.impl.resources;

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
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Order;

@Component(value="orderCacheLoadingCache")
@Qualifier("orderCacheLoadingCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCacheLoadingCacheCharacteristic implements BaseCharacteristic<LoadingCache<String,Order>>{
	
	/** @name 缓存最大数目 **/
	@NeededInject
	private static String Parameters_Module_Kernel_Cache_OrderCache_MaxSize;
	
	/** @name 缓存初始化数目 **/
	@NeededInject
	private static String Parameters_Module_Kernel_Cache_OrderCache_InitialSize;
	
	/** @name 缓存并行级别 **/
	@NeededInject
	private static String Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel;
	
	/** @name 缓存非读销毁时间 **/
	@NeededInject
	private static String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime;
	
	/** @name 缓存非写销毁时间 **/
	@NeededInject
	private static String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime;
	
	/**
	* @name cacheLoader
	* @illustrate Guava cacheLoader
	* **/
	@Resource(name="OrderCacheCacheLoaderCharacteristic")
	private Optional<CacheLoader<String, Order>> cacheLoader;
	
	/**
	* @name removalListener
	* @illustrate 移除监听器
	* **/
	@Resource(name="OrderCacheRemovalListenerCharacteristic")
	private Optional<RemovalListener<String, Order>> removalListener;
	
	/**
	* @name LoadingCache
	* @illustrate Guava loadingCache
	* **/
	private LoadingCache<String,Order> loadingCache;

	/**
	* @name Springs使用的初始化
	* @illustrate 基于AOP进行参数注入
	* **/
	private OrderCacheLoadingCacheCharacteristic() {}
	
	/**
	* @name 常规初始化
	* @illustrate 基于外部参数进行注入
	* **/
	private OrderCacheLoadingCacheCharacteristic(String Parameters_Module_Kernel_Cache_OrderCache_MaxSizeArg,
			                                     String Parameters_Module_Kernel_Cache_OrderCache_InitialSizeArg,
			                                     String Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevelArg,
			                                     String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTimeArg,
			                                     String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTimeArg) {
		
		Parameters_Module_Kernel_Cache_OrderCache_MaxSize = Parameters_Module_Kernel_Cache_OrderCache_MaxSizeArg;
		Parameters_Module_Kernel_Cache_OrderCache_InitialSize = Parameters_Module_Kernel_Cache_OrderCache_InitialSizeArg;
		Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel = Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevelArg;
		Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime = Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTimeArg;
		Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime = Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTimeArg;
	}
	
	@Override
	@Bean(name="orderCacheLoadingCacheCharacteristic")
	@Lazy(true)
	public Optional<LoadingCache<String, Order>> characteristic() {
		loadingCacheBuilder();
		return Optional.of(loadingCache);
	}
	
	private void loadingCacheBuilder(){
	     loadingCache = CacheBuilder
					   .newBuilder()
					   .concurrencyLevel(getIntegerParameter(Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel))
					   .expireAfterWrite(getIntegerParameter(Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime), TimeUnit.MILLISECONDS)
					   .initialCapacity(getIntegerParameter(Parameters_Module_Kernel_Cache_OrderCache_InitialSize))
					   .maximumSize(getIntegerParameter(Parameters_Module_Kernel_Cache_OrderCache_MaxSize))
					   .expireAfterAccess(getIntegerParameter(Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime), TimeUnit.MILLISECONDS)
					   .removalListener(removalListener.get())
					   .build(cacheLoader.get());
	     }
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
}