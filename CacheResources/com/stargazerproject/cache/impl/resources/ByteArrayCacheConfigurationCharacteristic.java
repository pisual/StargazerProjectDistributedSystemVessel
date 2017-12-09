package com.stargazerproject.cache.impl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

@Component(value="byteArrayCacheConfiguration")
@Qualifier("byteArrayCacheConfiguration")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheConfigurationCharacteristic implements BaseCharacteristic<Configuration>{
	
	@Autowired
	@Qualifier("byteArrayCacheCache")
	private BaseCharacteristic<CacheConfiguration> cacheConfiguration;
	
	private Configuration configuration;

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
	                 .addCache(cacheConfiguration.characteristic().get());
	}
	
}