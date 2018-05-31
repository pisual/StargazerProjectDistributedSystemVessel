package com.stargazerproject.order;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;

/** 
 *  @name 事件（EventAnalyze）接口
 *  @illustrate 事件的基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventExecute {
	
	/** @illustrate 开始执行事件, 执行者调用 
	 * 	@param      Optional<EventAnalysis> eventAnalysis : 事件运行器接口
	 * **/
	public void startEvent(Optional<EventAnalysis> eventAnalysis);
	
	/** @illustrate  跳过此事件**/
	public void skipEvent();
	
	
}
