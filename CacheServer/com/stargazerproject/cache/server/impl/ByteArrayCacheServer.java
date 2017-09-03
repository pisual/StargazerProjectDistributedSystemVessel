package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name OrderCache服务的实现
 *  @illustrate 继承于ServiceShell的OrderCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="byteArrayCacheServer")
@Qualifier("byteArrayCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("byteArrayCache")
	private StanderCharacteristicShell<BigCache<String, byte[]>> byteArrayCache;
	
	/** @construction 初始化构造 **/
	private ByteArrayCacheServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener", "bigCacheIndexCacheServerListener");
		Optional<BigCache<String, byte[]>> bigCache = BeanContainer.instance().getBean(Optional.of("byteArrayCacheInitialize"), Optional.class);
		byteArrayCache.initialize(bigCache);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}