package com.stargazerproject.cache.impl.resources;

import javax.annotation.Resource;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="byteArrayCacheConfiguration")
@Qualifier("byteArrayCacheConfiguration")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheConfigurationCharacteristic implements BaseCharacteristic<Configuration>{

	private Configuration configuration;
	
	@Resource(name="byteArrayCacheCacheConfigurationCharacteristic")
	private Optional<CacheConfiguration> cacheConfiguration;

	private ByteArrayCacheConfigurationCharacteristic() {}
	
	@Override
	@Bean(name="byteArrayCacheConfigurationCharacteristic")
	@Lazy(true)
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
	                 .addCache(cacheConfiguration.get());
	}
	
}