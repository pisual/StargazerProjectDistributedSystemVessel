package com.stargazerproject.cache.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

public final class ByteArrayCache extends BaseCacheImpl<String, byte[]> implements StanderCharacteristicShell<Cache<String, byte[]>>{

	/** @construction 初始化构造 **/
	public ByteArrayCache() {}
	
	@Override
	public void initialize(Optional<Cache<String, byte[]>> cacheArg) {
		cache = cacheArg.get();
	}
}
