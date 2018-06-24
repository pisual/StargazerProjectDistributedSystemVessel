package com.stargazerproject.cache.impl;

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

	private static final long serialVersionUID = 8694485613490173094L;
	
	@Autowired
	@Qualifier("bigCacheIndexCahce")
	private Cache<String, Map<String, Integer>> bigCahceIndexCache;
	
	/** @construction 初始化构造 **/
	public ByteArrayCache() {}
	
	@Override
	public void initialize(Optional<BigCache<String, byte[]>> bigCacheArg) {
		cache = bigCacheArg.get();
	}
	
	/** @illustrate get操作为获取当前Key下的所有分片数据，合并并返回为一个Byte数组，如果不存在这返回Optional德空值形式
	 *  @param @Optional Optional<String> key ,不可以为空值
	 *  @return @Optional Optional<byte[]> value ,可以为Optional的空值形式
	 * **/
	@Override
	public Optional<byte[]> get(Optional<String> key) {
		/**@illustrate 如果当前bigCahceIndexCache（分片索引缓存）中存在数据，则根据分片数目逐一获取并进行拼装数组**/
		if(bigCahceIndexCache.get(key).isPresent()){
			/**@illustrate 首先获取分片数组合计长度，创建合计数组，并置入第一分片数据**/
			byte[] resultByte = new byte[byteLenght(key)];
			int resultByteStartPoint = 0;
			byte[] firstCopyByte = super.cache.get(key).get();
			System.arraycopy(firstCopyByte, 0, resultByte, resultByteStartPoint, firstCopyByte.length); 
			resultByteStartPoint = resultByteStartPoint + firstCopyByte.length;
			
			/**@illustrate 如果存在其它分片则逐一置入**/
			if(lashIndex(key) > 1){
				for(int i = 2; i<=lashIndex(key); i++){
					byte[] CopyByte = super.cache.get(Optional.of(key.get() + "_" + i)).get();
					System.arraycopy(CopyByte, 0, resultByte, resultByteStartPoint, CopyByte.length); 
					resultByteStartPoint = resultByteStartPoint + CopyByte.length;
				}
			}
			return Optional.fromNullable(resultByte);

		}
		/**@illustrate 如果当前bigCahceIndexCache（分片索引缓存）中不存在数据，则表明需要获取的数据不存在，返回Optional的空值形式**/
		else{
			return Optional.absent();
		}
	}

	/** @illustrate Add操作用于大型数据缓存的分片操作，详细步骤如下
	 *              1. 需要检测bigCahceIndexCache（分片索引缓存）中是否存在需要分片的数据的Key，如果不存在调用Put操作来进行首次添加，
	 *                 如果已经存在数据的分片索引值，则正式进入分片操作。
	 *              2. 首先从bigCahceIndexCache中获取当前分片状态数据，并更新分片数据
	 *  @param @Optional Optional<String> key,不可以为空值
	 *  @param @Optional byte[] value， 可以为空值
	 * **/
	@Override
	public void add(Optional<String> key, byte[] value) {
		if(bigCahceIndexCache.get(key).isPresent()){
			/** @illustrate 分段锁，针对于同一Key进行锁定，不同Key之间操作不受影响 **/
			synchronized(key.get()){
				Integer lashIndex = bigCahceIndexCache.get(key).get().get("lashIndex");
				Integer byteLenght = bigCahceIndexCache.get(key).get().get("byteLenght");
				indexPointChange(key, lashIndex + 1, byteLenght + value.length); 		/** @illustrate 更新分片索引缓存，分片数目加1，分片数据总长进行叠加 **/
				put(Optional.of(key.get() + "_" + (lashIndex + 1)), value); /** @illustrate 置入分片数据到Bigcache缓存，命名ID方式：分片主ID_分片数目 **/
			}
			
		}
		/** @illustrate 如果bigCahceIndexCache索引缓存中没有Key的Value，则此元素从来没有置入过，采用Put操作来进行首次添加数据
		 * **/
		else{
			put(key, value);
		}
	}
	
	/** @illustrate Put操作用于元素的第一次置入，Put操作分为三个步骤
	 *              1.检测当前申请置入的元素Key在BigCache中是否存在，如果存在则抛出异常
	 *              2.如果不存在，则调用Put方法置入此元素
	 *              3.通过调用indexPointChange函数来更说分片索引，indexPointChange的参数为（key值，当前分片数（首次置入，分片则为1），当前分片总计数据长度）
	 *  	@param @Optional Optional<String> key,不可以为空值
	 *  @param @Optional byte[] value， 可以为空值
	 * **/
	@Override
	public void put(Optional<String> key, byte[] value) {
		synchronized(key.get()){
		/** @illustrate 如果缓存的Key已经存在，抛出异常并报告重复的Key**/
			if(get(key).isPresent()){
				throw new IllegalStateException("当前BigCache中已经存在 " + key.get() +" 不能重复置入，请使用Add方法进行分片添加");
				}
		/** @illustrate 如果缓存的Key不存在，需要进行初始化操作，
		 *              1. 置入数据到BigCache中
		 *              2. 还要更新分片索引缓存
		 **/
			else{
				super.put(key, value);
				indexPointChange(key, 1, value.length);
				}
			}
	}
	
	/**
	 * @illustrate 分片索引缓存更新分片索引操作，用与辅助缓存进行Add分片操作，
	 *             当大型数据进行第一次置入Put操作时，除了置入数据到BigCache中，还要更新分片索引缓存
	 *             indexPointChange的参数为（key值，当前分片数（首次置入，分片则为1），当前分片总计数据长度）
	 *  	@param @Optional Optional<String> key,不可以为空值
	 *  @param Integer LastIndex ，当前最新的分片数目
	 *  @param Integer byteLenght，当前合计数据长度
	 * **/
	private void indexPointChange(Optional<String> key, Integer LastIndex, Integer byteLenght){
		Map<String,Integer> indexPoint = new HashMap<String,Integer>();
		indexPoint.put("lashIndex", LastIndex);
		indexPoint.put("byteLenght", byteLenght);
		bigCahceIndexCache.put(key, Optional.of(indexPoint));
	}
	
	/**
	 * @illustrate 移除操作，移除当前Key下的所有分片数据，并删除bigCahceIndexCache（分片索引缓存）里的相应数据
	 *  @param @Optional Optional<String> key,不可以为空值
	 * **/
	@Override
	public void remove(Optional<String> key) {
		/** @illustrate 分段锁，针对于同一Key进行锁定，不同Key之间操作不受影响 **/
		synchronized(key.get()){
			super.remove(key);
			if(lashIndex(key) > 1){ /** @illustrate 如果分片数目大于1，则表明存在第二个分片 **/
				for(int i = 2; i<=lashIndex(key); i++){
					super.remove(Optional.of(key.get() + "_" + i));  /** @illustrate 按照命名规则移除所有分片 **/
				}
			}
			bigCahceIndexCache.remove(key);  /** @illustrate 最后移除分片索引缓存的数据 **/	
		}
	}
	
	/**
	 * @illustrate 根据Key获取当前分片的数目
	 * **/
	private Integer lashIndex(Optional<String> key){
		return bigCahceIndexCache.get(key).get().get("lashIndex");
	}
	
	/**
	 * @illustrate 根据Key获取当前分片合计的数据长度
	 * **/
	private Integer byteLenght(Optional<String> key){
		return bigCahceIndexCache.get(key).get().get("byteLenght");
	}

}
