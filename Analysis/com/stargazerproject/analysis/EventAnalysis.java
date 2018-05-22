package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;

/** 
 *  @name 事件运行器接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventAnalysis {
	
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache, Optional<Result> result);
	
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache, Optional<Cache<String, byte[]>> interactionByteArrayCache, Optional<Result> result);
}
