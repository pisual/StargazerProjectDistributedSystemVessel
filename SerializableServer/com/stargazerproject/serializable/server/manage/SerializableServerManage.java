package com.stargazerproject.serializable.server.manage;

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
 *  @name StandardServerManage服务集中托管
 *  @illustrate CellsGenerate服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="serializableServerManage")
@Qualifier("serializableServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(value="serializableServerManage", order = 0)
public class SerializableServerManage extends AbstractIdleService{
	
	/** @illustrate standardSequenceServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("serializableServer")
	private StanderServiceShell serializableServer;
	
	@Autowired
	@Qualifier("serializableServerListener")
	private Listener workInServiceControlListener;
	
	/** @construction 初始化构造 **/
	public SerializableServerManage() {}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		serializableServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		serializableServer.shutDown();
	}
}