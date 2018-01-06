package com.stargazerproject.queue.resources.shell;

import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
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
@Component(value="logDisruptorShell")
@Qualifier("logDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogDisruptorShell extends BaseQueueRingBuffer<LogData, LogQueueEvent> implements BaseCharacteristic<Queue<LogData>>{
	
	/** @name 接收Log队列的缓存数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Receive_Log_Size_of_bufferSize;
	
	/** @name 接收Log队列的消费者数目 **/
	@NeededInject(type="SystemParametersCache")
	private static String Receive_Log_Number_of_consumers;
	
	@Autowired
	@Qualifier("logEventFactory")
	private EventFactory<LogQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("logQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("cleanLogHandler")
	private WorkHandler<LogQueueEvent> cleanLogHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private LogDisruptorShell() {
		super.translator = new EventTranslatorOneArg<LogQueueEvent, LogData>() {
			public void translateTo(LogQueueEvent logQueueEvent, long sequence, LogData logData) {
				logQueueEvent.setLogData(logData);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LogDisruptorShell(Optional<ThreadFactory> threadFactoryArg, Optional<EventFactory<LogQueueEvent>> eventFactoryArg, Optional<WorkHandler<LogQueueEvent>> cleanLogHandlerArg) {
		eventFactory = eventFactoryArg.get();
		threadFactory = threadFactoryArg.get();
		cleanLogHandler = cleanLogHandlerArg.get();
		
		super.translator = new EventTranslatorOneArg<LogQueueEvent, LogData>() {
			public void translateTo(LogQueueEvent logQueueEvent, long sequence, LogData logData) {
				logQueueEvent.setLogData(logData);
			}
		};
	}
	
	@Override
	public Optional<Queue<LogData>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		disruptor = new Disruptor<LogQueueEvent>(eventFactory, getIntegerParameter(Receive_Log_Size_of_bufferSize), threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanLogHandler);
	}
	
	private void handleEvents(){
		handler = new LogHandler[getIntegerParameter(Receive_Log_Number_of_consumers)];
		for(int i=0; i<getIntegerParameter(Receive_Log_Number_of_consumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("logHandler"), WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
}