package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.QueueConsumer;

@Component(value="eventConsumer")
@Qualifier("eventConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventConsumer implements QueueConsumer<Event>{

	@Autowired
	@Qualifier("eventExecute")
	private EventExecute execute;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	protected EventConsumer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventConsumer(Optional<EventExecute> executeArg) {
		execute = executeArg.get();
	}
	@Override
	public void consumer(Optional<Event> e) {
		e.get().startEvent(Optional.of(execute));
	}

}
