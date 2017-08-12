package com.stargazerproject.cache.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;


@Component(value="byteArrayCache")
@Qualifier("byteArrayCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class ByteArrayCache extends BaseCacheImpl<String, byte[]> implements StanderCharacteristicShell<BigCache<String, byte[]>>{
	
	/** @construction 初始化构造 **/
	public ByteArrayCache() {}
	
	@Override
	public void initialize(Optional<BigCache<String, byte[]>> cacheArg) {
		cache = cacheArg.get();
	}
}
