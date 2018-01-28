package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.queue.QueueConsumer;

@Component(value="transmissionConsumer")
@Qualifier("transmissionConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransmissionConsumer implements QueueConsumer<Transmission>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionConsumer() {}
	
	@Override
	public void consumer(Optional<Transmission> e) {
		System.out.println("Transmission 注入队列的具体操作");
	}
	
}
