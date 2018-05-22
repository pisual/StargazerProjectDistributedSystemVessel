package com.stargazerproject.order.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.EventState;
import com.stargazerproject.order.Result;

/** 
 *  @name 可追踪注入序列实体
 *              Event状态 ： 
 *                         NoComplete：未完成状态
 *                         Skip：
 *  @author Felixerio
 *  **/
/** 
 *  @name 事件结果（EventResult）实现
 *  @illustrate 事件（Event）是事务的原子单位，一个事件包含了一个事务，并包含了对于这个事务
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class Event extends ID{
	
	/** @illustrate 事件结果接口**/
	private Result result;
	
	/** @illustrate 事件状态**/
	private EventState eventState;
	
	/** @illustrate 交互缓存 **/
	public Cache<String, String> interactionCache;

	/** @illustrate Event 构造函数
	 *@param			Optional<String> idArg ： 聚合根ID（Order ID）
	 *@param			Optional<Cache<String, String>> interactionCacheArg : 交互缓存的实现
	 *@param			Optional<Result> resultArg : 事件结果的实现
	 * **/
	public Event(Optional<String> idArg, Optional<Cache<String, String>> interactionCacheArg, Optional<Result> resultArg) {
		super(idArg);
		result = resultArg.get();
		interactionCache = interactionCacheArg.get();
		eventState = EventState.WAIT;
	}

	/** @illustrate 开始执行事件, 执行者接口 **/
	public void startEvent(Optional<EventAnalysis> eventAnalysis) {
		eventState = EventState.RUN;
		eventAnalysis.get().analysis(Optional.of(interactionCache), Optional.of(result));
		eventState = EventState.COMPLETE;
	}
	
	/** @illustrate 获取并且分析事件结果, 调用者接口 **/
	public void eventResult(){
		
	}
	
	/** @illustrate  跳过此事件**/
	public void skipEvent(){
		eventState = Boolean.FALSE;
	}
	
	/** @illustrate 获取事件状态**/
	public Optional<EventState> eventState(){
		return Optional.of(eventState);
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("result", result)
                          .add("parameter", parameter).toString();
	}
}