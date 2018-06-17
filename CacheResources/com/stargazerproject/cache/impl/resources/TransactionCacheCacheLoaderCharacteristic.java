package com.stargazerproject.cache.impl.resources;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionCacheCacheLoaderCharacteristic")
@Qualifier("transactionCacheCacheLoaderCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionCacheCacheLoaderCharacteristic implements BaseCharacteristic<CacheLoader<String, Transaction>>{

	private CacheLoader<String, Transaction> cacheLoader;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionCacheCacheLoaderCharacteristic() {}
	
	@Override
	public Optional<CacheLoader<String, Transaction>> characteristic() {
		initializationCacheLoader();
		return Optional.of(cacheLoader);
	}
	
	
	private void initializationCacheLoader(){
		cacheLoader = new CacheLoader<String, Transaction>(){
			@Override
			public Transaction load(String key) throws ExecutionException {
				throw new ExecutionException("事务缓存键 : "+ key +" 不存在 : Transaction Cache Key Inexistence", new Throwable());
			}
		};
	}
	
}