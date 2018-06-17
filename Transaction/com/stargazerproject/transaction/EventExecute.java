package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 事件运行（EventExecute）接口
 *  @illustrate 事件的运行的基础方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventExecute {
	
	/** @illustrate 开始执行事件, 执行者调用 
	 * 	@param      Optional<EventExecuteAnalysis> eventAnalysis : 事件运行器接口，不可以为空值
	 * 	@ThreadSafeMethodsLevel eventExecute的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void eventExecute(Optional<EventExecuteAnalysis> eventAnalysis);
	
	/** @illustrate  跳过此事件
	 *  @ThreadSafeMethodsLevel skipEvent的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void skipEvent();
	
	
}
