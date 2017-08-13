package com.stargazerproject.cache.impl.resources.shell;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.model.order.impl.Order;

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
public class OrderCahceShell implements Cache<String, Order>, BaseCharacteristic<Cache<String, Order>>{

	/** @illustrate 通用LoadingCache Guava 缓存接口 **/
	@Resource(name="orderCacheLoadingCacheCharacteristic")
	protected LoadingCache<String, Order> loadingCache;
	
	/** @construction 初始化构造 **/
	public OrderCahceShell() {}
	
	@Override
	@Bean(name="orderCahceCharacteristicInitialize")
	@Lazy(true)
	public Optional<Cache<String, Order>> characteristic() {
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
			throw new NullPointerException("Key :"+key.get()+" 的Value不存在");
		}
	}

	@Override
	public void remove(Optional<String> key) {
		loadingCache.invalidate(key.get());
	}

}
