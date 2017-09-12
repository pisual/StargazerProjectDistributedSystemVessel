package com.stargazerproject.queue.impl.resources.shell;

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
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.LogQueueEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.LogHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name LogQueue
 *  @Shell 
 *  @illustrate 
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component(value="logQueueShell")
@Qualifier("logQueueShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogDisruptorShell extends BaseQueueRingBuffer<LogData, LogQueueEvent> implements BaseCharacteristic<Queue<LogData>>{
	
	@Autowired
	@Qualifier("logEventFactory")
	private EventFactory<LogQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("logQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	private LogDisruptorShell() {
		super.translator = new EventTranslatorOneArg<LogQueueEvent, LogData>() {
			public void translateTo(LogQueueEvent logQueueEvent, long sequence, LogData logData) {
				logQueueEvent.setLogData(logData);
			}
		};
	}
	
	@Override
	@Bean(name="logQueueCharacteristicInitialize")
	@Lazy(true)
	public Optional<Queue<LogData>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		Integer bufferSize = Integer.parseInt(cache.get(Optional.of("Receive_Log_Size_of_bufferSize")).get());
		disruptor = new Disruptor<LogQueueEvent>(eventFactory, bufferSize, threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(handler);
	}
	
	private void handleEvents(){
		Integer logConsumersNumber = Integer.parseInt(cache.get(Optional.of("Receive_Log_Number_of_consumers")).get());
		handler = new LogHandler[logConsumersNumber];
		for(int i=0; i<logConsumersNumber; i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("logHandler"), WorkHandler.class);
		}
	}
	
}