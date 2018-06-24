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
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionCacheLoadingCacheCharacteristic")
@Qualifier("transactionCacheLoadingCacheCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionCacheLoadingCacheCharacteristic implements BaseCharacteristic<LoadingCache<String,Transaction>>{
	
	/** @name 缓存最大数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_TransactionCache_MaxSize;
	
	/** @name 缓存初始化数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_TransactionCache_InitialSize;
	
	/** @name 缓存并行级别 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevel;
	
	/** @name 缓存非读销毁时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTime;
	
	/** @name 缓存非写销毁时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTime;
	
	/**
	* @name cacheLoader
	* @illustrate Guava cacheLoader
	* **/
	@Autowired
	@Qualifier("transactionCacheCacheLoaderCharacteristic")
	private BaseCharacteristic<CacheLoader<String, Transaction>> cacheLoader;
	
	/**
	* @name removalListener
	* @illustrate 移除监听器
	* **/
	@Autowired
	@Qualifier("transactionCacheRemovalListenerCharacteristic")
	private BaseCharacteristic<RemovalListener<String, Transaction>> removalListener;
	
	/**
	* @name LoadingCache
	* @illustrate Guava loadingCache
	* **/
	private LoadingCache<String, Transaction> loadingCache;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransactionCacheLoadingCacheCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionCacheLoadingCacheCharacteristic(Optional<String> Parameters_Module_Kernel_Cache_TransactionCache_MaxSizeArg,
			                                          Optional<String> Parameters_Module_Kernel_Cache_TransactionCache_InitialSizeArg,
			                                          Optional<String> Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevelArg,
			                                          Optional<String> Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTimeArg,
			                                          Optional<String> Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTimeArg,
			                                          Optional<BaseCharacteristic<CacheLoader<String, Transaction>>> cacheLoaderArg,
			                                          Optional<BaseCharacteristic<RemovalListener<String, Transaction>>> removalListenerArg,
			                                          Optional<LoadingCache<String,Transaction>> loadingCacheArg) {
		
		Parameters_Module_Kernel_Cache_TransactionCache_MaxSize = Parameters_Module_Kernel_Cache_TransactionCache_MaxSizeArg.get();
		Parameters_Module_Kernel_Cache_TransactionCache_InitialSize = Parameters_Module_Kernel_Cache_TransactionCache_InitialSizeArg.get();
		Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevel = Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevelArg.get();
		Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTime = Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTimeArg.get();
		Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTime = Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTimeArg.get();
		cacheLoader = cacheLoaderArg.get();
		loadingCache = loadingCacheArg.get();
		removalListener = removalListenerArg.get();
	}
	
	@Override
	public Optional<LoadingCache<String, Transaction>> characteristic() {
		loadingCacheBuilder();
		return Optional.of(loadingCache);
	}
	
	private void loadingCacheBuilder(){
	     loadingCache = CacheBuilder
					   .newBuilder()
					   .concurrencyLevel(getIntegerParameter(Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevel))
					   .expireAfterWrite(getIntegerParameter(Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTime), TimeUnit.MILLISECONDS)
					   .initialCapacity(getIntegerParameter(Parameters_Module_Kernel_Cache_TransactionCache_InitialSize))
					   .maximumSize(getIntegerParameter(Parameters_Module_Kernel_Cache_TransactionCache_MaxSize))
					   .expireAfterAccess(getIntegerParameter(Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTime), TimeUnit.MILLISECONDS)
					   .removalListener(removalListener.characteristic().get())
					   .build(cacheLoader.characteristic().get());
	     }
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Parameters_Module_Kernel_Cache_TransactionCache_MaxSize", Parameters_Module_Kernel_Cache_TransactionCache_MaxSize)
                .add("Parameters_Module_Kernel_Cache_TransactionCache_InitialSize", Parameters_Module_Kernel_Cache_TransactionCache_InitialSize)
                .add("Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevel", Parameters_Module_Kernel_Cache_TransactionCache_ConcurrencyLevel)
                .add("Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTime", Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterReadTime)
                .add("Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTime", Parameters_Module_Kernel_Cache_TransactionCache_ExpireAfterWriteTime).toString();
	}
}