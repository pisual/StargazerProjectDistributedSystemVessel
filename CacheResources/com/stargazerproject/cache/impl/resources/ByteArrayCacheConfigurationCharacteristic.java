package com.stargazerproject.cache.impl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

@Component(value="byteArrayCacheConfigurationCharacteristic")
@Qualifier("byteArrayCacheConfigurationCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheConfigurationCharacteristic implements BaseCharacteristic<Configuration>{
	
	@Autowired
	@Qualifier("byteArrayCacheCacheConfigurationCharacteristic")
	private BaseCharacteristic<CacheConfiguration> cacheConfiguration;
	
	private Configuration configuration;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private ByteArrayCacheConfigurationCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ByteArrayCacheConfigurationCharacteristic(Optional<BaseCharacteristic<CacheConfiguration>> cacheConfigurationArg) {
		cacheConfiguration = cacheConfigurationArg.get();
	}
	
	@Override
	public Optional<Configuration> characteristic() {
		initializatioConfiguration();
		return Optional.of(configuration);
	}
	
	
	@SuppressWarnings("deprecation")
	private void initializatioConfiguration(){
		configuration = new Configuration();
		configuration.updateCheck(true)
		             .monitoring(Configuration.Monitoring.AUTODETECT)
	                 .name("byteArrayCache")
	                 .addCache(cacheConfiguration.characteristic().get());
	}
	
}