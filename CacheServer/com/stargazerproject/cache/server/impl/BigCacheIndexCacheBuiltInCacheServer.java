package com.stargazerproject.cache.server.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;

/** 
 *  @name bigCacheIndexCache服务的实现
 *  @illustrate 继承于ServiceShell的bigCacheIndexCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="bigCacheIndexCacheBuiltInCacheServer")
@Qualifier("bigCacheIndexCacheBuiltInCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BigCacheIndexCacheBuiltInCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("bigCacheIndexCahce")
	private StanderCharacteristicShell<Cache<String, Map<String, Integer>>> bigCacheIndexCahce;
	
	@Autowired
	@Qualifier("bigCacheIndexCahceShell")
	private BaseCharacteristic<Cache<String, Map<String, Integer>>> bigCacheIndexCahceShell;
	
	private Cache<String, Map<String, Integer>> bigCacheIndexCahceCharacteristic;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BigCacheIndexCacheBuiltInCacheServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BigCacheIndexCacheBuiltInCacheServer(Optional<StanderCharacteristicShell<Cache<String, Map<String, Integer>>>> bigCacheIndexCahceArg,
			                                    Optional<BaseCharacteristic<Cache<String, Map<String, Integer>>>> bigCacheIndexCahceShellArg) {
	
		bigCacheIndexCahce = bigCacheIndexCahceArg.get();
		bigCacheIndexCahceShell = bigCacheIndexCahceShellArg.get();
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener");
		bigCacheIndexCahceCharacteristic = bigCacheIndexCahceShell.characteristic().get();
		bigCacheIndexCahce.initialize(Optional.of(bigCacheIndexCahceCharacteristic));
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}