package com.stargazerproject.sequence;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.cache.Cache;

public interface SequenceTransaction<K>{
	
	/**
	* @name 创建序列组
	* @illustrate 创建序列组，如果已经存在，将抛出IllegalArgumentException(" .. Group exist")异常
	* @param Optional<String> sequenceGroup 序列的命名
	* **/
	public SequenceTransaction<K> creatSequenceGroup(Optional<String> sequenceGroup);
	
	/**
	* @name 添加聚合根缓存
	* @illustrate 聚合根缓存将贯穿整个序列过程，提供参数传导
	* @param Optional<Cache<String, String>> cache 聚合根缓存
	* **/
	public SequenceTransaction<K> addSequenceCache(Optional<Cache<String, String>> cache);
	
	/**
	* @name 添加事务
	* @illustrate 添加事务
	* @param Optional<K> event 事务
	* **/
	public SequenceTransaction<K> addModel(Optional<K> event);
	
	/**
	* @name 清除指定的Sequence队列
	* @illustrate 清除指定的Sequence队列
	* @param Optional<String> sequenceGroup 队列的Group名字
	* **/
	public void clear(Optional<String> sequenceGroup);
	
	/**
	* @name 启动指定的Sequence队列
	* @illustrate 启动指定的Sequence队列
	* @param Optional<String> sequenceGroup 队列的Group名字
	* **/
	public void startSequence(Optional<String> sequenceGroup) throws BusEventTimeoutException;
}
