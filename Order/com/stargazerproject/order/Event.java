package com.stargazerproject.order;

import com.google.common.base.Optional;

public interface Event extends EventExecute, EventAnalyze{
	
	/** @illustrate 获取事件状态 
	 *  @return     Optional<EventState> : 结果状态 EventState
	 * **/
	public Optional<EventState> eventState();

}
