package com.stargazer.segmentation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.order.base.impl.BaseEvent;
import com.stargazerproject.queue.Queue;

@Component
@Qualifier("eventSegmentation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventSegmentation implements Segmentation<Optional<BaseEvent>>{
	
	@Autowired
	@Qualifier("eventQueue")
	public Queue<BaseEvent> eventQueue;
	
	@Override
	public void batchSegmentation(Optional<BaseEvent> event) {
		eventQueue.producer(event);
	}
}
