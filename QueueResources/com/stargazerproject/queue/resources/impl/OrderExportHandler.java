package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.consumer.impl.OrderExportConsumer;
import com.stargazerproject.queue.model.OrderExportEvent;

/** 
 *  @name Event队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="orderExportHandler")
@Qualifier("orderExportHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderExportHandler extends OrderExportConsumer implements WorkHandler<OrderExportEvent> {
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderExportHandler(){
		super();
	}
	
//	/**
//	* @name 常规初始化构造
//	* @illustrate 基于外部参数进行注入
//	* **/
//	public OrderExportHandler(Optional<EventExecuteAnalysis> executeArg) {
//		super(executeArg);
//	}

	@Override
	public void onEvent(OrderExportEvent event){
		super.consumer(Optional.of(event.getOrder()));
	}
	
}
