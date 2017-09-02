package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component
@Qualifier("logEventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogEventFactory extends QueueEventFactory<LogData>{
	
	/** @construction 初始化构造 **/
	private LogEventFactory() {
		super();
		}

	@Override
	public LogData newInstance() {
		return new LogData();
	}
	
}
