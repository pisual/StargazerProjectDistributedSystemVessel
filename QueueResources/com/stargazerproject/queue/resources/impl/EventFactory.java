package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.QueueEventFactory;

@Component
@Qualifier("eventFactory")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventFactory extends QueueEventFactory<EventQueueEvent>{
	@Override
	public EventQueueEvent newInstance() {
		return new EventQueueEvent();
	}
	
}
