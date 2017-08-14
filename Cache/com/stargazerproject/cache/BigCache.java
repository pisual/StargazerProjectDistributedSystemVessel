package com.stargazerproject.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.base.Optional;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public interface BigCache<K, V> {
	
	/**
	 * @name 置入
	 * @illustrate 缓存内容置入
	 * @param <K> 缓存的Key值
	 * @param <V> 缓存的Value值
	 * @Optional Guava包装
	 * **/
	public void put(Optional<K> key, V value);
	
	/**
	 * @name 添加（分片添加）
	 * @illustrate 在原有的索引ID后面加上 _1 ,重新建立缓存存入，用于消除反复合并Byte数组造成的性能缺失
	 * 1, 主键 uuid_1  :   Byte[]
	 * 2, 添加新的Byte Byte[]_2
	 * 3, 建立新的索引 附键 uuid_2 : Byte[]_2
	 * 4, 同时更新主键为 uuid_1_2,用于追踪最后一位的存储ID位置
	 * @param <K> 缓存的Key值
	 * @param <V> 缓存的Value值
	 * @Optional Guava包装
	 * **/
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
	
	/**
	 * @name 检测Key是否存在
	 * @illustrate 检测Key是否存在
	 * @param <K> 缓存的Key值
	 * **/
	public boolean isExist(Optional<K> key);

}
