package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name OrderCache服务的实现
 *  @illustrate 继承于ServiceShell的OrderCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="byteArrayCache")
@Qualifier("byteArrayCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheServer extends ByteArrayCache implements StanderServiceShell{
	
	/** @construction 初始化构造 **/
	private ByteArrayCacheServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<BigCache<String, byte[]>> bigCache = BeanContainer.instance().getBean(Optional.of("byteArrayCacheBigCacheCharacteristic"), Optional.class);
		initialize(bigCache);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}