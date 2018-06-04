package com.stargazer.segmentation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.order.Event;
import com.stargazerproject.queue.Queue;

@Component
@Qualifier("eventSegmentation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventSegmentation implements Segmentation<Optional<Event>>{
	
	@Autowired
	@Qualifier("eventQueue")
	public Queue<Event> eventQueue;
	
	@Override
	public void batchSegmentation(Optional<Event> event) {
		eventQueue.producer(event);
	}
}
