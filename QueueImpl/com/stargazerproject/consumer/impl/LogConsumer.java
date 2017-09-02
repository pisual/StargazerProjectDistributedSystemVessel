package com.stargazerproject.consumer.impl;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.QueueConsumer;

public class LogConsumer implements QueueConsumer<LogData>{

	public LogConsumer() {}
	
	@Override
	public void consumer(Optional<LogData> e) {
	}

}
