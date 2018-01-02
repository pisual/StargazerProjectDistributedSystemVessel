package com.stargazerproject.cache.impl.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.order.impl.Order;

@Component(value="orderCacheLoadingCacheCharacteristic")
@Qualifier("orderCacheLoadingCacheCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCacheLoadingCacheCharacteristic implements BaseCharacteristic<LoadingCache<String,Order>>{
	
	/** @name 缓存最大数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_OrderCache_MaxSize;
	
	/** @name 缓存初始化数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_OrderCache_InitialSize;
	
	/** @name 缓存并行级别 **/
	@NeededInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel;
	
	/** @name 缓存非读销毁时间 **/
	@NeededInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime;
	
	/** @name 缓存非写销毁时间 **/
	@NeededInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime;
	
	/**
	* @name cacheLoader
	* @illustrate Guava cacheLoader
	* **/
	@Autowired
	@Qualifier("orderCacheCacheLoaderCharacteristic")
	private BaseCharacteristic<CacheLoader<String, Order>> cacheLoader;
	
	/**
	* @name removalListener
	* @illustrate 移除监听器
	* **/
	@Autowired
	@Qualifier("orderCacheRemovalListenerCharacteristic")
	private BaseCharacteristic<RemovalListener<String, Order>> removalListener;
	
	/**
	* @name LoadingCache
	* @illustrate Guava loadingCache
	* **/
	private LoadingCache<String,Order> loadingCache;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderCacheLoadingCacheCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderCacheLoadingCacheCharacteristic(Optional<String> Parameters_Module_Kernel_Cache_OrderCache_MaxSizeArg,
			                                    Optional<String> Parameters_Module_Kernel_Cache_OrderCache_InitialSizeArg,
			                                    Optional<String> Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevelArg,
			                                    Optional<String> Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTimeArg,
			                                    Optional<String> Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTimeArg,
			                                    Optional<BaseCharacteristic<CacheLoader<String, Order>>> cacheLoaderArg,
			                                    Optional<BaseCharacteristic<RemovalListener<String, Order>>> removalListenerArg,
			                                    Optional<LoadingCache<String,Order>> loadingCacheArg) {
		
		Parameters_Module_Kernel_Cache_OrderCache_MaxSize = Parameters_Module_Kernel_Cache_OrderCache_MaxSizeArg.get();
		Parameters_Module_Kernel_Cache_OrderCache_InitialSize = Parameters_Module_Kernel_Cache_OrderCache_InitialSizeArg.get();
		Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel = Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevelArg.get();
		Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime = Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTimeArg.get();
		Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime = Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTimeArg.get();
		cacheLoader = cacheLoaderArg.get();
		loadingCache = loadingCacheArg.get();
		removalListener = removalListenerArg.get();
	}
	
	@Override
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
					   .removalListener(removalListener.characteristic().get())
					   .build(cacheLoader.characteristic().get());
	     }
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Parameters_Module_Kernel_Cache_OrderCache_MaxSize", Parameters_Module_Kernel_Cache_OrderCache_MaxSize)
                .add("Parameters_Module_Kernel_Cache_OrderCache_InitialSize", Parameters_Module_Kernel_Cache_OrderCache_InitialSize)
                .add("Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel", Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel)
                .add("Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime", Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime)
                .add("Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime", Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime).toString();
	}
}