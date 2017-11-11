package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueConsumer;

public class EventBusConsumer implements QueueConsumer<Event>{

	@Autowired
	@Qualifier("eventBusExecute")
	private EventExecute execute;
	
	@Autowired
	@Qualifier("logQueue")
	public Queue<LogData> logQueue;
	
	public EventBusConsumer() {}
	
	@Override
	public void consumer(Optional<Event> e) {
		e.get().startEvent(Optional.of(execute));
	}

}
