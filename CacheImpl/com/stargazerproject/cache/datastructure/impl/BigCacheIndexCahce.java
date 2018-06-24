package com.stargazerproject.cache.datastructure.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

/** 
 *  @name Bigcache的分片索引缓存
 *  @illustrate Bigcache的分片索引缓存，用于辅助Bigcache进行分片存储数据
 *  @param <String> 缓存的Key值类型
 *  @param <Map<String, Integer>> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="bigCacheIndexCahce")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("bigCacheIndexCahce")
public final class BigCacheIndexCahce extends BaseDataStructureCache<String, Map<String, Integer>> {
	
	private static final long serialVersionUID = 7278965105821281064L;

	/** @construction 初始化构造 **/
	public BigCacheIndexCahce() {}

}