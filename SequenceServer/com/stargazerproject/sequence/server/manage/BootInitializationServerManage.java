package com.stargazerproject.sequence.server.manage;

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
 *  @name CellsGenerateServer服务集中托管
 *  @illustrate CellsGenerate服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="bootInitializationServerManage")
@Qualifier("bootInitializationServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Services(value="bootInitializationServerManage", order = 99)
public class BootInitializationServerManage extends AbstractIdleService{
	
	/** @illustrate cellsGenerateSequenceServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("bootInitializationSequenceServer")
	private StanderServiceShell cellsGenerateSequenceServer;
	
	@Autowired
	@Qualifier("bootInitializationServerListener")
	private Listener workInServiceControlListener;
	
	/** @construction 初始化构造 **/
	public BootInitializationServerManage() {}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		cellsGenerateSequenceServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		cellsGenerateSequenceServer.shutDown();
	}
}