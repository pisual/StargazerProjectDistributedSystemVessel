package com.stargazer.segmentation;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public interface Segmentation<K> {
	/** 
	 *  @name 缓存接口
	 *  @illustrate 实现缓存的基础功能
	 *  @param <K> 缓存的Key值类型
	 *  @param <V> 缓存的Value类型
	 *  @author Felixerio
	 *  **/
	public void batchSegmentation(K k);
}
