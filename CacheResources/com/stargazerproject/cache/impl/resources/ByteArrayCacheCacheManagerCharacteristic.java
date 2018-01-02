package com.stargazerproject.cache.impl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

@Component(value="byteArrayCacheCacheManagerCharacteristic")
@Qualifier("byteArrayCacheCacheManagerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheCacheManagerCharacteristic implements BaseCharacteristic<CacheManager>{

	@Autowired
	@Qualifier("byteArrayCacheConfigurationCharacteristic")
	private BaseCharacteristic<Configuration> byteArrayCacheConfigurationCharacteristic;
	
	private Configuration cacheConfiguration;
	
	private CacheManager manager;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private ByteArrayCacheCacheManagerCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ByteArrayCacheCacheManagerCharacteristic(Optional<Configuration> byteArrayCacheConfigurationCharacteristic) {
		cacheConfiguration = byteArrayCacheConfigurationCharacteristic.get();
	}
	
	@Override
	public Optional<CacheManager> characteristic() {
		initializatioCacheManager();
		return Optional.of(manager);
	}
	
	
	private void initializatioCacheManager(){
		cacheConfiguration= byteArrayCacheConfigurationCharacteristic.characteristic().get();
		manager = CacheManager.create(cacheConfiguration);
	}
	
}