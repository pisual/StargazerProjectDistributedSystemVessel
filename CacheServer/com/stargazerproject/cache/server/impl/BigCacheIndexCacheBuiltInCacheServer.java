package com.stargazerproject.cache.server.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

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
	
	/** @construction 初始化构造 **/
	private BigCacheIndexCacheBuiltInCacheServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener");
		Optional<Cache<String, Map<String, Integer>>> mapArg = BeanContainer.instance().getBean(Optional.of("bigCacheIndexCahceCharacteristicInitialize"), Optional.class);
		bigCacheIndexCahce.initialize(mapArg);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}