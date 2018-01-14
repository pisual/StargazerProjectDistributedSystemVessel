package com.stargazerproject.messagequeue.server.manage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.MoreExecutors;
import com.stargazerproject.service.annotation.Services;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name orderMessageQueue服务集中托管
 *  @illustrate orderMessageQueue服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="orderMessageQueueServerManage")
@Qualifier("orderMessageQueueServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(value="orderMessageQueueServerManage", order = 8)
public class OrderMessageQueueServerManage extends AbstractIdleService{
	
	/** @illustrate orderCacheServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("orderMessageQueueServer")
	private StanderServiceShell orderMessageQueueServer;
	
	@Autowired
	@Qualifier("orderMessageQueueServerListener")
	private Listener workInServiceControlListener;
	
	/** @construction 初始化构造 **/
	public OrderMessageQueueServerManage() {}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		orderMessageQueueServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		orderMessageQueueServer.shutDown();
	}

}