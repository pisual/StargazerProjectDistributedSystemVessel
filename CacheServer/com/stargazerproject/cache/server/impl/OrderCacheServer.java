package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.LoadingCache;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name OrderCache服务的实现
 *  @illustrate 继承于ServiceShell的OrderCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="orderCacheServer")
@Qualifier("orderCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("orderCache")
	private StanderCharacteristicShell<LoadingCache<String,Order>> orderCache;
	
	/** @construction 初始化构造 **/
	private OrderCacheServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<LoadingCache<String, Order>> loadingCache = BeanContainer.instance().getBean(Optional.of("orderCahceCharacteristicInitialize"), Optional.class);
		orderCache.initialize(loadingCache);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}