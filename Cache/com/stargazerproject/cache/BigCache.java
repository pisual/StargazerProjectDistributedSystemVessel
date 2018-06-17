package com.stargazerproject.cache;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 大型数据缓存接口
 *  @illustrate 实现大型数据缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public interface BigCache<K, V> extends Serializable{
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <V> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel 依赖具体的实现方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
	public void put(Optional<K> key, V value);
	
	/**
	 * @name 添加（分片添加）
	 * @illustrate 在原有的索引ID后面加上 _1 ,重新建立缓存存入，用于消除反复合并Byte数组造成的性能缺失
	 * 1, 主键 uuid_1  :   Byte[]
	 * 2, 添加新的Byte Byte[]_2
	 * 3, 建立新的索引 附键 uuid_2 : Byte[]_2
	 * 4, 同时更新主键为 uuid_1_2,用于追踪最后一位的存储ID位置
	 * @param  @Optional <K> 缓存的Key值,不允许空值
	 * @param  @Optional <V> 缓存的Value值,不允许空值
	 * @ThreadSafeMethodsLevel 有条件的线程安全方法，针对于同一ID是线程不安全的
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void add(Optional<K> key, V value);
	
	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param <K> 缓存的Key值
	 * @return <V> 缓存的Value值
	 * @exception ExecutionException
	 * @Optional Guava包装
	 * **/
	public Optional<V> get(Optional<K> key);
	
	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param <K> 缓存的Key值
	 * @Optional Guava包装
	 * **/
	public void remove(Optional<K> key);

}
