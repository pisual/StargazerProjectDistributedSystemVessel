package com.stargazerproject.cache.impl.resources.shell;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("byteArrayCacheBigCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheBigCacheShell implements BigCache<String,byte[]>, BaseCharacteristic<BigCache<String,byte[]>>{
	
	private Optional<CacheManager> manager;
	
	private Cache cache;
	
	public ByteArrayCacheBigCacheShell() {}

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
		return Optional.of((byte[]) cache.get(key.get()).getObjectValue());
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="byteArrayCacheBigCacheInitialize")
	@Lazy(true)
	public Optional<BigCache<String, byte[]>> characteristic() {
		manager= BeanContainer.instance().getBean(Optional.of("byteArrayCacheCacheManagerCharacteristic"),Optional.class);
		cache = manager.get().getCache("sample-cache");
		return Optional.of(this);
	}

}
