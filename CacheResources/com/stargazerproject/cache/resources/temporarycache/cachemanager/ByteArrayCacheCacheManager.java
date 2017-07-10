package com.stargazerproject.cache.resources.temporarycache.cachemanager;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("byteArrayCacheCacheManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheCacheManager implements BaseCharacteristic<CacheManager>{

	private CacheManager manager;
	
	private Optional<Configuration> cacheConfiguration;

	private ByteArrayCacheCacheManager() {}
	
	@Override
	@Bean(name="byteArrayCacheCacheManagerCharacteristic")
	@Lazy(true)
	public Optional<CacheManager> characteristic() {
		initializatioCacheManager();
		return Optional.of(manager);
	}
	
	@SuppressWarnings("unchecked")
	private void initializatioCacheManager(){
		cacheConfiguration= BeanContainer.instance().getBean(Optional.of("byteArrayCacheConfigurationCharacteristic"),Optional.class);
		manager = CacheManager.create(cacheConfiguration.get());
	}
	
}