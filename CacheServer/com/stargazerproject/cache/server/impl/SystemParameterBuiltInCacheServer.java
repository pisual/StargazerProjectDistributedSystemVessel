package com.stargazerproject.cache.server.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name OrderCache服务的实现
 *  @illustrate 继承于ServiceShell的OrderCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="systemParameterBuiltInCacheServer")
@Qualifier("systemParameterBuiltInCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterBuiltInCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private StanderCharacteristicShell<Map<String,String>> SystemParameterCache;
	
	/** @construction 初始化构造 **/
	private SystemParameterBuiltInCacheServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener");
		Optional<Map<String, String>> mapArg = BeanContainer.instance().getBean(Optional.of("systemParameterCahceCharacteristicInitialize"), Optional.class);
		SystemParameterCache.initialize(mapArg);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}