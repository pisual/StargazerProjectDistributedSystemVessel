package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueConsumer;


public class EventConsumer implements QueueConsumer<Event>{

	@Autowired
	@Qualifier("eventExecute")
	private EventExecute execute;
	
	@Autowired
	@Qualifier("logQueue")
	public Queue<LogData> logQueue;
	
	int i = 0;
	
	public EventConsumer() {}
	
	@Override
	public void consumer(Optional<Event> e) {
		e.get().startEvent(Optional.of(execute));
		logQueue.producer(Optional.of(new LogData(Optional.of("指令数目"), Optional.of(i+""), Optional.of(LogLevel.INFO))));
		i++;
	}

}
