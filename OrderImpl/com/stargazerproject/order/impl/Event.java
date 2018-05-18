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
public class Event extends ID{
	
	/** @illustrate 事件参数接口,需要注入baseMapUnit **/
	public Cache<String, String> parameter;
	
	/** @illustrate 事件结果接口**/
	private Result result;
	
	/** @illustrate 事件状态**/
	private EventState eventState;

	/** @illustrate  加参数初始化 **/
	public Event(Optional<String> idArg, Optional<Cache<String, String>> parameterArg) {
		super(idArg);
		parameter = parameterArg.get();
	}
	
	public boolean isComplete(){
		return result.isComplete();
	}

	/** @illustrate  开始执行事件 **/
	public void startEvent(Optional<EventAnalysis> eventAnalysis) {
		result = new ResultVoid();
		if(eventAnalysis.get().analysis(Optional.of(parameter)).get()){
			result.Complete();
			}
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