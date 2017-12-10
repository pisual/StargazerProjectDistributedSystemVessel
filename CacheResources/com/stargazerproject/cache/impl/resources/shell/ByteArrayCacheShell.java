package com.stargazerproject.cache.impl.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.characteristic.BaseCharacteristic;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component(value="ByteArrayCacheShell")
@Qualifier("byteArrayCacheShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheShell implements BigCache<String, byte[]>, BaseCharacteristic<BigCache<String, byte[]>>{
	
	@Autowired
	@Qualifier("byteArrayCacheCacheManager")
	private BaseCharacteristic<CacheManager> byteArrayCacheCacheManager;
	
	private CacheManager manager;
	
	private Cache cache;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private ByteArrayCacheShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ByteArrayCacheShell(Optional<BaseCharacteristic<CacheManager>> byteArrayCacheCacheManagerArg) {
		byteArrayCacheCacheManager = byteArrayCacheCacheManagerArg.get();
	}

	@Override
	public void put(Optional<String> key, byte[] value) {
		Element element = new Element(key.get(), value);
		cache.put(element);
	}

	@Override
	public void add(Optional<String> key, byte[] value) {
	}

	@Override
	public Optional<byte[]> get(Optional<String> key) {
		return Optional.fromNullable((byte[]) cache.get(key.get()).getObjectValue());
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

	@Override
	@Bean(name="byteArrayCacheInitialize")
	@Lazy(true)
	public Optional<BigCache<String, byte[]>> characteristic() {
		manager= byteArrayCacheCacheManager.characteristic().get();
		cache = manager.getCache("byteArrayCache");
		return Optional.of(this);
	}

}
