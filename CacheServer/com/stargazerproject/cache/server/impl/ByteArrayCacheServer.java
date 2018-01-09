package com.stargazerproject.cache.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

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
	
	@Autowired
	@Qualifier("ByteArrayCacheShell")
	private BaseCharacteristic<BigCache<String, byte[]>> ByteArrayCacheShell;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private ByteArrayCacheServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ByteArrayCacheServer(Optional<BaseCharacteristic<BigCache<String, byte[]>>> ByteArrayCacheShellArg, Optional<StanderCharacteristicShell<BigCache<String, byte[]>>> byteArrayCacheArg){
		ByteArrayCacheShell = ByteArrayCacheShellArg.get();
		byteArrayCache = byteArrayCacheArg.get();
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		byteArrayCache.initialize(ByteArrayCacheShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}