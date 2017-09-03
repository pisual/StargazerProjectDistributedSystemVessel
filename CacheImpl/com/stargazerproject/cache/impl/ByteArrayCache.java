package com.stargazerproject.cache.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseBigCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;


@Component(value="byteArrayCache")
@Qualifier("byteArrayCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class ByteArrayCache extends BaseBigCacheImpl<String, byte[]> implements StanderCharacteristicShell<BigCache<String, byte[]>>{
	
	@Autowired
	@Qualifier("bigCacheIndexCahce")
	private Cache<String, Map<String, Integer>> bigCahceIndexCache;
	
	/** @construction 初始化构造 **/
	public ByteArrayCache() {}
	
	@Override
	public void initialize(Optional<BigCache<String, byte[]>> bigCacheArg) {
		cache = bigCacheArg.get();
	}
	
	@Override
	public Optional<byte[]> get(Optional<String> key) {
		if(null == bigCahceIndexCache.get(key).orNull()){
			return cache.get(key);
		}
		else{
			byte[] resultByte = new byte[byteLenght(key)];
			int resultByteStartPoint = 0;
			byte[] firstCopyByte = cache.get(key).get();
			System.arraycopy(firstCopyByte, 0, resultByte, resultByteStartPoint, firstCopyByte.length); //5个参数  原数组 起始index  数组2   起始index  拷贝长度  
			resultByteStartPoint = resultByteStartPoint + firstCopyByte.length;
			
			if(lashIndex(key) > 1){
				for(int i = 2; i<=lashIndex(key); i++){
					byte[] CopyByte = cache.get(Optional.of(key.get() + "_" + i)).get();
					System.arraycopy(CopyByte,0,resultByte,resultByteStartPoint,CopyByte.length); //5个参数  原数组 起始index  数组2   起始index  拷贝长度 
					resultByteStartPoint = resultByteStartPoint + CopyByte.length;
				}
			}
			return Optional.fromNullable(resultByte);
		}
	}

	@Override
	public void add(Optional<String> key, byte[] value) {
		if(null == bigCahceIndexCache.get(key).orNull()){
			put(key, value);
			indexPointChange(key, 1, value.length);
		}
		else{
			Integer lashIndex = bigCahceIndexCache.get(key).get().get("lashIndex");
			Integer byteLenght = bigCahceIndexCache.get(key).get().get("byteLenght");
			indexPointChange(key, lashIndex + 1, byteLenght + value.length);
			put(Optional.of(key.get() + "_" + (lashIndex + 1)), value);
		}
	}
	
	@Override
	public void put(Optional<String> key, byte[] value) {
		super.put(key, value);
		indexPointChange(key, 1, value.length);
	}
	
	private void indexPointChange(Optional<String> key, Integer LastIndex, Integer byteLenght){
		if(byteLenght<0){
			throw new IllegalAccessError("The files exceeded the maximum limit");
		}
		Map<String,Integer> indexPoint = new HashMap<String,Integer>();
		indexPoint.put("lashIndex", LastIndex);
		indexPoint.put("byteLenght", byteLenght);
		bigCahceIndexCache.put(key, Optional.of(indexPoint));
	}
	
	@Override
	public void remove(Optional<String> key) {
		super.remove(key);
		if(lashIndex(key) > 1){
			for(int i = 2; i<=lashIndex(key); i++){
				super.remove(Optional.of(key.get() + "_" + i));
			}
		}
	}
	
	private Integer lashIndex(Optional<String> key){
		return bigCahceIndexCache.get(key).get().get("lashIndex");
	}
	
	private Integer byteLenght(Optional<String> key){
		return bigCahceIndexCache.get(key).get().get("byteLenght");
	}

}
