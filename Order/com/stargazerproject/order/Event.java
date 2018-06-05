package com.stargazerproject.order;

import com.google.common.base.Optional;

public interface Event extends EventAssemble, EventExecute, EventAnalyze, Entity<String>{
	
	/** @illustrate 获取事件状态 
	 *  @return     Optional<EventState> : 结果状态 EventState
	 * **/
	public Optional<EventState> eventState();

}
