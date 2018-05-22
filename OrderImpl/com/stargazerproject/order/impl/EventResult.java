package com.stargazerproject.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;
import com.stargazerproject.order.ResultState;


/** 
 *  @name 事件结果（EventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public final class EventResult implements Result{
	
	/** @illustrate 事件结果内容缓存**/
	private Cache<String, String> executionResultCache;
	
	/** @illustrate EventResult的构造函数，需要注入Optional<Cache<String, String>>的一个实现作为运行结果缓存execution Result Cache**/
	public EventResult(Optional<Cache<String, String>> executionResultCacheArg) {
		executionResultCache = executionResultCacheArg.get();
	}
	
	/** @illustrate 设置事件结果标志为完成状态，**/
	@Override
	public void complete() {
		executionResultCache.put(Optional.of("Result"), Optional.of(ResultState.SUCCESS.toString()));
	}

	/** @illustrate 获取当前事件结果标志（ResultState）的状态**/
	@Override
	public Optional<String> resultState() {
		return executionResultCache.get(Optional.of("Result"));
	}

}