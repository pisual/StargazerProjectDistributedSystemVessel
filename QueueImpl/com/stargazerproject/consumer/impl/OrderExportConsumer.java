package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueConsumer;


public class OrderExportConsumer implements QueueConsumer<Order>{

	@Autowired
	@Qualifier("eventExecute")
	private EventExecute execute;
	
	@Autowired
	@Qualifier("logQueue")
	public Queue<LogData> logQueue;
	
	public OrderExportConsumer() {}
	
	@Override
	public void consumer(Optional<Order> e) {
		System.out.println("Order 已经放置到输出队列 " + e.get().toString());
	}

}
