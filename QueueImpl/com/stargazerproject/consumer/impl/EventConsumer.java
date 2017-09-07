package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Execute;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.QueueConsumer;


public class EventConsumer implements QueueConsumer<Event>{

	@Autowired
	@Qualifier("eventExecute")
	private Execute execute;
	
	public EventConsumer() {}
	
	@Override
	public void consumer(Optional<Event> e) {
		e.get().startEvent(Optional.of(execute));
	}

}
