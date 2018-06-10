package com.stargazerproject.cache.impl.resources.shell;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.impl.Order;

/** 
 *  @name Order缓存
 *  @Shell LoadingCache<K, V> loadingCache，Guava LoadingCache 类型的通用接口
 *  @illustrate 在指定条件下发生解出操作的缓存，
 *              1.自主解出
 *              2.超时间解出
 *              3.超容量解出
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component(value="orderCahceShell")
@Qualifier("orderCahceShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCahceShell implements  Cache<String, Order>, BaseCharacteristic<Cache<String, Order>>{

	@Autowired
	@Qualifier("orderCacheLoadingCacheCharacteristic")
	private BaseCharacteristic<LoadingCache<String,Order>> loadingCacheBaseCharacteristic;
	
	/** @illustrate 通用LoadingCache Guava 缓存接口 **/
	protected LoadingCache<String, Order> loadingCache;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderCahceShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderCahceShell(Optional<BaseCharacteristic<LoadingCache<String,Order>>> loadingCacheBaseCharacteristicArg) {
		loadingCacheBaseCharacteristic = loadingCacheBaseCharacteristicArg.get();
	}
	
	@Override
	public Optional<Cache<String, Order>> characteristic() {
		loadingCache = loadingCacheBaseCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	public void put(Optional<String> key, Optional<Order> value) {
		loadingCache.put(key.get(), value.get());
	}

	@Override
	public Optional<Order> get(Optional<String> key) {
		try {
			return Optional.of(loadingCache.get(key.get()));
		} catch (ExecutionException e) {
			throw new NullPointerException("Stargazer ServiceControlServer Report :  Key : "+key.get()+" Value is Null");
		}
	}

	@Override
	public void remove(Optional<String> key) {
		loadingCache.invalidate(key.get());
	}

}