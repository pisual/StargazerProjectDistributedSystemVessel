package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.EventAssemble;

/** 
 *  @name 事件装配器接口
 *  @illustrate 实现缓存装配的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionAssembleAnalysis {
	
	public Optional<Boolean> analysis(Optional<List<EventAssemble>> eventsList);
	
	public void addEvent(Optional<Event> event);
}
