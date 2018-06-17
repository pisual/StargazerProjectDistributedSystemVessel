package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name Event接口
 *  @illustrate Event的基础功能
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public interface Event extends EventAssemble, EventExecute, EventResult, Entity<String>{
	
	/** @illustrate 获取事件状态 
	 *  @return     Optional<EventState> : 结果状态 EventState
	 *  @ThreadSafeMethodsLevel eventState的线程安全级别为ThreadSafeLevel.ThreadSafe，线程安全
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<EventState> eventState();

}
