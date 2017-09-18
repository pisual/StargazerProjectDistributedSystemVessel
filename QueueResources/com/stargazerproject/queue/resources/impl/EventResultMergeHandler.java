package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.State;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.Queue;
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
	
	@Autowired
	@Qualifier("OrderExportQueue")
	private Queue<Order> OrderExportQueue;
	
	/** @construction 初始化构造 **/
	public EventResultMergeHandler() {}

	@Override
	public void onEvent(EventQueueEvent event){
		Order order = cache.get(event.getEvent().IDSequence()).get();
		if(order.checkResult() && order.state().get().equals(State.Execute)){
			order.changeState(Optional.of(State.Send));
			System.out.println("Order归并成功 " + order.toString());
			OrderExportQueue.producer(Optional.of(order));
		}
		else{
			System.out.println("Order 已经归并，将抛弃本次归并" + order.toString());
		}
	}
	
}
