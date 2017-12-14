package com.stargazerproject.cache.server.manage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.MoreExecutors;
import com.stargazerproject.service.baseinterface.Services;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name systemParameterCache服务集中托管
 *  @illustrate OrderCache服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="systemParameterCacheServerManage")
@Qualifier("systemParameterCacheServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(value="systemParameterCacheServerManage", order = 2)
public class SystemParameterCacheServerManage extends AbstractIdleService{
	
	/** @illustrate orderCacheServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("systemParameterBuiltInCacheServer")
	private StanderServiceShell systemParameterCacheServer;
	
	@Autowired
	@Qualifier("systemParameterCacheServerListener")
	private Listener workInServiceControlListener;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private SystemParameterCacheServerManage() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SystemParameterCacheServerManage(Optional<StanderServiceShell> systemParameterCacheServerArg, Optional<Listener> workInServiceControlListenerArg) {
		systemParameterCacheServer = systemParameterCacheServerArg.get();
		workInServiceControlListener = workInServiceControlListenerArg.get();
	}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		systemParameterCacheServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		systemParameterCacheServer.shutDown();
	}
}