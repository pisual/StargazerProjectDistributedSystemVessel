package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.LogQueueEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component
@Qualifier("logEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogEventFactory extends QueueEventFactory<LogQueueEvent>{
	
	/** @construction 初始化构造 **/
	private LogEventFactory() {
		super();
		}

	@Override
	public LogQueueEvent newInstance() {
		return new LogQueueEvent();
	}
	
}
