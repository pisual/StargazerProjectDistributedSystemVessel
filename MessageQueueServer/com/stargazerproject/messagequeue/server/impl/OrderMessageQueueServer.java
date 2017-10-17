package com.stargazerproject.messagequeue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueSpringIntegrationAcquire;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueControlCharacteristic;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueSpringIntegrationPush;
import com.stargazerproject.messagequeue.resources.shell.OrderMessageQueueShall;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name orderMessageQueue服务的实现
 *  @illustrate 继承于ServiceShell的orderMessageQueue相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="orderMessageQueueServer")
@Qualifier("orderMessageQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("orderMessageQueue")
	private StanderCharacteristicShell<MessageQueue<Order>> orderMessageQueueShell;
	
	/** @construction 初始化构造 **/
	private OrderMessageQueueServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener", "bigCacheIndexCacheServerListener");
		Optional<MessageQueue<Order>> orderMessageQueue = BeanContainer.instance().getBean(Optional.of("orderMessageQueueCharacteristicInitialize"), Optional.class);
		orderMessageQueueShell.initialize(orderMessageQueue);
		
		/**Topic Initialization**/
	//	orderMessageQueue.get().join();
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void shutDown() {
		Optional<MessageQueue<Order>> orderMessageQueue = BeanContainer.instance().getBean(Optional.of("orderMessageQueueCharacteristicInitialize"), Optional.class);
		
		/**Topic Destroy**/
		orderMessageQueue.get().out();;
	}
	
}