package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.consumer.impl.EventBusConsumer;
import com.stargazerproject.queue.model.EventQueueEvent;

/** 
 *  @name Event队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="eventBusHandler")
@Qualifier("eventBusHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusHandler extends EventBusConsumer implements WorkHandler<EventQueueEvent> {
	
	/** @construction 初始化构造 **/
	public EventBusHandler() {}

	@Override
	public void onEvent(EventQueueEvent event){
		super.consumer(Optional.of(event.getEvent()));
	}
	
}
