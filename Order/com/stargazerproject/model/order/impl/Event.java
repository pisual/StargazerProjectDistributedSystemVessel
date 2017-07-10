package com.stargazerproject.model.order.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Execute;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.model.order.Result;

/** 
 *  @name 可追踪注入序列实体dddddddd
 *  @illustrate 带有ID变量的可追踪的实体，由外界进行序列注入ddddd
 *  @author Felixerio
 *  **/
@Component
@Qualifier("event")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class Event extends ID{
	
	/** @illustrate 事件参数接口,需要注入baseMapUnit **/
	private Cache<String, String> parameter;
	
	/** @illustrate 事件结果接口**/
	private Result result;

	/** @illustrate  加参数初始化 **/
	public Event(Optional<String> idArg, Optional<Cache<String, String>> parameterArg) {
		super(idArg);
		parameter = parameterArg.get();
	}

	/** @illustrate  开始执行事件 **/
	public void startEvent(Optional<Execute> execute) {
		execute.get().executeEvent(Optional.of(parameter), Optional.of(result));
	}
}