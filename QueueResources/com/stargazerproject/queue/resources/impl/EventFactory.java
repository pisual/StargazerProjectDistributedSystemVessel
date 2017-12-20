package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component(value="eventFactory")
@Qualifier("eventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventFactory extends QueueEventFactory<EventQueueEvent>{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventFactory() {
		super();
	}
	
	@Override
	public EventQueueEvent newInstance() {
		return new EventQueueEvent();
	}
	
}
