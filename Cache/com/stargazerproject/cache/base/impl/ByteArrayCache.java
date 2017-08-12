package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.base.impl.TemporaryBigMemoryCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

public class ByteArrayCache extends TemporaryBigMemoryCacheImpl<String, byte[]> implements StanderCharacteristicShell<BigCache<String,byte[]>>{
	
	protected ByteArrayCache() {
		super();
	}

	/**
	* @name 添加到现有的对象
	* @illustrate 原有内容A，添加内容B，add后内容:A+B,此功能对于系统的负担非常大，请谨慎使用。
	* @param <Optional<String>> 索引
	* @param <byte[]> 缓存的Value值
	* **/
	@Override
	public void add(Optional<String> key, byte[] value) {
		int sourceLenght = get(key).length;
		int targerLenght = value.length;
		byte[] result = new byte[sourceLenght + targerLenght];
		System.arraycopy(get(key), 0, result, 0, sourceLenght);
		System.arraycopy(value, 0, result, sourceLenght, targerLenght);
		update(key, result);
	}
	
	private void update(Optional<String> key, byte[] value){
		remove(key);
		put(key, value);
	}

	@Override
	public void initialize(Optional<BigCache<String, byte[]>> bigCacheArg) {
		cache = bigCacheArg.get();
	}

}
