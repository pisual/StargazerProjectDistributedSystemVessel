package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name Event结果队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="eventResultMergeHandler")
@Qualifier("eventResultMergeHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultMergeHandler implements WorkHandler<EventQueueEvent> {
	
	@Autowired
	@Qualifier("transactionCache")
	private Cache<String, Transaction> transactionCache;
	
	@Autowired
	@Qualifier("transactionExportQueue")
	private Queue<Transaction> transactionExportQueue;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventResultMergeHandler() {
		super();
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventResultMergeHandler(Optional<Cache<String, Transaction>> transactionCacheArg, Optional<Queue<Transaction>> transactionExportQueueArg){
		transactionCache = transactionCacheArg.get();
		transactionExportQueue = transactionExportQueueArg.get();
	}

	@Override
	public void onEvent(EventQueueEvent event){
		
	}
	
}
