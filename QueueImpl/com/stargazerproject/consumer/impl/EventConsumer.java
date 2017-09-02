package com.stargazerproject.consumer.impl;

import com.google.common.base.Optional;
import com.stargazerproject.model.order.impl.Event;
import com.stargazerproject.queue.QueueConsumer;

public class EventConsumer implements QueueConsumer<Event>{

	public EventConsumer() {}
	
	@Override
	public void consumer(Optional<Event> e) {
	}

}
