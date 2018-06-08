package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

/** 
 *  @name 事件装配器接口
 *  @illustrate 实现缓存装配的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventAssembleAnalysis {
	
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache);
	
}
