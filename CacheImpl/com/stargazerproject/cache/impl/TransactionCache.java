package com.stargazerproject.cache.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name transaction(事务)缓存
 *  @illustrate 事务缓存，事务缓存是对事务的第一级缓存，并同时承担着超时控制，
 *              一旦超过最长等待处理时间，将抛弃此事务，通过调用Transaction的Skip来主动放弃并进行快速失败
 *  @Shell Cache<String,Transaction>，Map类型的通用接口
 *  @param <String> 缓存的Key值类型
 *  @param <Transaction> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="transactionCache")
@Qualifier("transactionCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class TransactionCache extends BaseCacheImpl<String,Transaction> implements StanderCharacteristicShell<Cache<String,Transaction>>{
	
	private static final long serialVersionUID = -6399058194166202599L;

	/** @construction 初始化构造 **/
	public TransactionCache() {}

	@Override
	public void initialize(Optional<Cache<String, Transaction>> cacaheArg) {
		cache = cacaheArg.get();
	}

}