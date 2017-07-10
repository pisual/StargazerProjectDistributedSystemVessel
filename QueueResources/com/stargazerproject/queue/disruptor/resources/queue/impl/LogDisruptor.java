package com.stargazerproject.queue.disruptor.resources.queue.impl;

import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.disruptor.resources.queue.BaseQueueRingBuffer;
import com.stargazerproject.queue.handler.impl.LogHandler;

@Component
@Qualifier("logDisruptor")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogDisruptor extends BaseQueueRingBuffer<LogData> implements BaseCharacteristic<Queue<LogData>>{
	
	@Autowired
	@Qualifier("logEventFactory")
	private EventFactory<LogData> eventFactory;
	
	@Autowired
	@Qualifier("logQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	private LogDisruptor() {}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Log_Size_of_bufferSize")).get());
		disruptor = new Disruptor<LogData>(eventFactory, bufferSize, threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(handler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Log_Number_of_consumers")).get());
		handler = new LogHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = new LogHandler();
		}
	}

	@Override
	@Bean(name="logDisruptorQueueCharacteristic")
	@Lazy(true)
	public Optional<Queue<LogData>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
}