package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 事件（EventResult）接口
 *  @illustrate 事件的基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventResult {
	
	/** @illustrate 分析事件结果，分析者调用
	 *  @param      Optional<EventResultAnalysis> eventResultAnalysis : 事件结果分析器接口，不可以为空值
	 *  	@ThreadSafeMethodsLevel eventResult的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void eventResult(Optional<EventResultAnalysis> eventResultAnalysis);
	
}
