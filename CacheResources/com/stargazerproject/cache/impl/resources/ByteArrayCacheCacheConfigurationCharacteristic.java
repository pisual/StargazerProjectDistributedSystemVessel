package com.stargazerproject.cache.impl.resources;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="byteArrayCacheCache")
@Qualifier("byteArrayCacheCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheCacheConfigurationCharacteristic implements BaseCharacteristic<CacheConfiguration>{

	private CacheConfiguration cacheConfiguration;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ByteArrayCacheCacheConfigurationCharacteristic() {}
	
	@Override
	@Bean(name="byteArrayCacheCacheConfigurationCharacteristic")
	@Lazy(true)
	public Optional<CacheConfiguration> characteristic() {
		initializatioConfiguration();
		return Optional.of(cacheConfiguration);
	}
	
	
	private void initializatioConfiguration(){
		cacheConfiguration = new CacheConfiguration()
	                             .name("byteArrayCache")
	                             .maxBytesLocalHeap(128, MemoryUnit.MEGABYTES)
	                             .maxBytesLocalOffHeap(1, MemoryUnit.GIGABYTES)
	                             .timeToLiveSeconds(100)
	                             .timeToIdleSeconds(100);
	}
	
}