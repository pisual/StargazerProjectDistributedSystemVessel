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
		System.err.println("OnlineLog Debug # " + message);
	}

	@Override
	public void INFO(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.INFO))));
		System.err.println("OnlineLog Info # " + message);
	}

	@Override
	public void WARN(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.WARN))));
		System.err.println("OnlineLog Warn # " + message);
	}

	@Override
	public void ERROR(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.DEBUG))));
		System.err.println("OnlineLog Error # " + message);
	}

	@Override
	public void FATAL(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.FATAL))));
		System.err.println("OnlineLog Fatal # " + message);
	}

}
