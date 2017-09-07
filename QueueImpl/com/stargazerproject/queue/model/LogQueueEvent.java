package com.stargazerproject.queue.model;

import com.google.common.base.MoreObjects;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.order.impl.Event;

/** 
 *  @name lmax disruptor 专用的EventModel
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class LogQueueEvent {
	
	private LogData logData;

	public LogData getLogData() {
		return logData;
	}

	public void setLogData(LogData logData) {
		this.logData = logData;
	}
	
}
