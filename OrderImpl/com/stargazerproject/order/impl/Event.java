package com.stargazerproject.order.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;

/** 
 *  @name 可追踪注入序列实体
 *  @illustrate 带有ID变量的可追踪的实体，由外界进行序列注入
 *  @author Felixerio
 *  **/
public final class Event extends ID{
	
	/** @illustrate 事件参数接口,需要注入baseMapUnit **/
	public Cache<String, String> parameter;
	
	/** @illustrate 事件结果接口**/
	private Result result;

	/** @illustrate  加参数初始化 **/
	public Event(Optional<String> idArg, Optional<Cache<String, String>> parameterArg) {
		super(idArg);
		parameter = parameterArg.get();
	}
	
	public boolean isComplete(){
		return result.isComplete();
	}

	/** @illustrate  开始执行事件 **/
	public void startEvent(Optional<EventExecute> execute) {
		result = new ResultVoid();
		execute.get().executeEvent(Optional.of(parameter), Optional.of(result));
		result.Complete();
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("result", result).add("parameter Test", parameter.get(Optional.of("Test")))
                          .add("parameter", parameter).toString();
	}
}