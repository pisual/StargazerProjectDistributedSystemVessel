package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

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
	private StanderCharacteristicShell<Cache<String, Order>> orderCache;
	
	@Autowired
	@Qualifier("orderCahceShell")
	private BaseCharacteristic<Cache<String, Order>> orderCahceShell;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OrderCacheServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OrderCacheServer(Optional<BaseCharacteristic<Cache<String, Order>>> orderCahceShellArg, Optional<StanderCharacteristicShell<Cache<String, Order>>> orderCacheArg){
		orderCahceShell = orderCahceShellArg.get();
		orderCache = orderCacheArg.get();
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		orderCache.initialize(orderCahceShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}