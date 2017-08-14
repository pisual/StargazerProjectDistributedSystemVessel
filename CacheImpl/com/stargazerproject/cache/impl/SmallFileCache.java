package com.stargazerproject.cache.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseBigCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.model.order.impl.Order;


@Component(value="byteArrayCache")
@Qualifier("byteArrayCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class SmallFileCache extends BaseBigCacheImpl<String, byte[]> implements StanderCharacteristicShell<BigCache<String, byte[]>>{
	
	@Autowired
	@Qualifier("BigCahceIndexCache")
	private Cache<String,Integer> bigCahceIndexCache;
	
	/** @construction 初始化构造 **/
	public SmallFileCache() {}
	
	@Override
	public void initialize(Optional<BigCache<String, byte[]>> bigCacheArg) {
		cache = bigCacheArg.get();
	}
	
	@Override
	public Optional<byte[]> get(Optional<String> key) {
		if(null == bigCahceIndexCache.get(key).orNull()){
			return cache.get(key);
		}
		else{
			
		}
		return cache.get(key);
	}

	@Override
	public void add(Optional<String> key, byte[] value) {
		if(null == bigCahceIndexCache.get(key).orNull()){
			put(key, value);
			bigCahceIndexCache.put(key, Optional.of(1));
		}
		else{
			Integer indexLast = bigCahceIndexCache.get(key).get() + 1;
			put(Optional.of(key.get() + "_" +indexLast), value);
			bigCahceIndexCache.put(key, Optional.of(indexLast));
		}
	}
	
	@Override
	public void put(Optional<String> key, byte[] value) {
		super.put(key, value);
		bigCahceIndexCache.put(key, Optional.of(1));
	}
}
