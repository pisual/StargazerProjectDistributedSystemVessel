package com.stargazerproject.analysis;

import java.util.Collection;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventAssemble;

/** 
 *  @name 事件装配器接口
 *  @illustrate 实现缓存装配的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionAssembleAnalysis {
	
	public Optional<Boolean> analysis(Optional<Collection<EventAssemble>> eventsList);
	
	public void addEvent(Optional<EventAssemble> eventAssemble);
}
