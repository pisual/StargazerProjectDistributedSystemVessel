package com.stargazerproject.cache.server.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name systemParameterCache服务的实现
 *  @illustrate 继承于ServiceShell的systemParameterCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="systemParameterCacheServer")
@Qualifier("systemParameterCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterCacheServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private StanderCharacteristicShell<Cache<String,String>> SystemParameterCache;
	
	@Autowired
	@Qualifier("systemParameterCahceShell")
	private BaseCharacteristic<Cache<String, String>> systemParameterCahceShell;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private SystemParameterCacheServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SystemParameterCacheServer(Optional<BaseCharacteristic<Cache<String, String>>> systemParameterCahceShellArg, Optional<StanderCharacteristicShell<Cache<String,String>>> SystemParameterCacheArg){
		systemParameterCahceShell = systemParameterCahceShellArg.get();
		SystemParameterCache = SystemParameterCacheArg.get();
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		SystemParameterCache.initialize(systemParameterCahceShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}