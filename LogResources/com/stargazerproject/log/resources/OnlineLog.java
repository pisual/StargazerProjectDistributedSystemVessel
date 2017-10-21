package com.stargazerproject.log.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.log.Log;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.queue.Queue;

public class OnlineLog implements Log{
	
	@Autowired
	@Qualifier("logQueue")
	public Queue<LogData> queue;
	
	@Override
	public void DEBUG(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.DEBUG))));
	}

	@Override
	public void INFO(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.INFO))));
	}

	@Override
	public void WARN(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.WARN))));
	}

	@Override
	public void ERROR(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.ERROR))));
	}

	@Override
	public void FATAL(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.FATAL))));
	}

}
