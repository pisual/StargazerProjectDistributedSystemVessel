package com.stargazerproject.queue.model;

import com.stargazerproject.log.model.LogData;

/** 
 *  @name lmax disruptor 专用的EventModel
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class LogQueueEvent {
	
	private LogData logData;
	
    public void clear(){
    	    logData = null;
	}

	public LogData getLogData() {
		return logData;
	}

	public void setLogData(LogData logData) {
		this.logData = logData;
	}
	
}
