package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.lmax.disruptor.WorkHandler;
import com.stargazerproject.consumer.impl.TransmissionConsumer;
import com.stargazerproject.queue.model.TransmissionEvent;

/** 
 *  @name TransmissionQueue队列的消费者
 *  @illustrate 队列的消费者功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="transmissionQueueHandler")
@Qualifier("transmissionQueueHandler")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransmissionQueueHandler extends TransmissionConsumer implements WorkHandler<TransmissionEvent> {
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	private TransmissionQueueHandler(){
		super();
	}

	@Override
	public void onEvent(TransmissionEvent transmissionEvent){
		super.consumer(Optional.of(transmissionEvent.getTransmission()));
	}
	
}
