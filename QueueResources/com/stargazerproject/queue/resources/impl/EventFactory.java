package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component
@Qualifier("eventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventFactory extends QueueEventFactory<Event>{
	
	/** @construction 初始化构造 **/
	private EventFactory() {
		super();
		}

	@Override
	public Event newInstance() {
		return new Event();
	}
	
}
