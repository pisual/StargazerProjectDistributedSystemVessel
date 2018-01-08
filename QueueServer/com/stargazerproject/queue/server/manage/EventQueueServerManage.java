package com.stargazerproject.queue.server.manage;

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
 *  @name OrderCache服务集中托管
 *  @illustrate OrderCache服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="eventQueueServerManage")
@Qualifier("eventQueueServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(value="eventQueueServerManage", order = 10)
public class EventQueueServerManage extends AbstractIdleService{
	
	/** @illustrate orderCacheServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("eventQueueServer")
	private StanderServiceShell eventQueueServer;
	
	@Autowired
	@Qualifier("eventQueueServerListener")
	private Listener workInServiceControlListener;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventQueueServerManage() {
		super();
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventQueueServerManage(Optional<StanderServiceShell> eventQueueServerArg, Optional<Listener> workInServiceControlListenerArg) {
		super();
		eventQueueServer = eventQueueServerArg.get();
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
		eventQueueServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		eventQueueServer.shutDown();
	}

}
