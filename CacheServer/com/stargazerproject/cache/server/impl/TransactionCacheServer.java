package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name TransactionCache服务的实现
 *  @illustrate 继承于ServiceShell的TransactionCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="transactionCacheServer")
@Qualifier("transactionCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("transactionCache")
	private StanderCharacteristicShell<Cache<String, Transaction>> transactionCache;
	
	@Autowired
	@Qualifier("transactionCahceShell")
	private BaseCharacteristic<Cache<String, Transaction>> transactionCahceShell;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private TransactionCacheServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionCacheServer(Optional<BaseCharacteristic<Cache<String, Transaction>>> transactionCahceShellArg, Optional<StanderCharacteristicShell<Cache<String, Transaction>>> transactionCacheArg){
		transactionCahceShell = transactionCahceShellArg.get();
		transactionCache = transactionCacheArg.get();
	}
	
	/** @illustrate 启动服务及相关操作
	 *  @ThreadSafeMethodsLevel startUp方法的线程安全级别是 ThreadSafeLevel.ThreadCompatible，非线程安全方法
	 *  **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	@Override
	public void startUp() {
		transactionCache.initialize(transactionCahceShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 
	 *  @ThreadSafeMethodsLevel shutDown方法的线程安全级别是 ThreadSafeLevel.ThreadCompatible，非线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	@Override
	public void shutDown() {
	}
}