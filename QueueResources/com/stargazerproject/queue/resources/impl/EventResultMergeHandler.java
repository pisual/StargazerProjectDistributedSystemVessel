package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.model.EventQueueEvent;

/** 
 *  @name Event队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("eventResultMergeHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultMergeHandler implements WorkHandler<EventQueueEvent> {
	
	@Autowired
	@Qualifier("orderCache")
	private Cache<String, Order> cache;
	
	/** @construction 初始化构造 **/
	public EventResultMergeHandler() {}

	@Override
	public void onEvent(EventQueueEvent event){
		Order order = cache.get(event.getEvent().IDSequence()).get();
		if(order.checkResult()){
			System.out.println("指令已经完成");
		}
	}
	
}
