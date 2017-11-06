package com.stargazerproject.consumer.impl;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.queue.QueueConsumer;

public class LogConsumer implements QueueConsumer<LogData>{

	public LogConsumer() {}
	
	@Override
	public void consumer(Optional<LogData> e) {
		if(e.get().logLevel().get().equals(LogLevel.ERROR) || (e.get().logLevel().get().equals(LogLevel.FATAL))){
			System.err.println("Stargazer System Report : " + e.get().toString());
		}
		else{
			System.out.println("Stargazer System Report : " + e.get().toString());
		}
	}
	
}
