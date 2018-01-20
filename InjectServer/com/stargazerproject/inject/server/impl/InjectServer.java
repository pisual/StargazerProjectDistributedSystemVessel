package com.stargazerproject.inject.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.inject.Inject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="injectServer")
@Qualifier("injectServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("injectImpl")
	private StanderCharacteristicShell<Inject> injectImpl;
	
	@Autowired
	@Qualifier("injectShell")
	private BaseCharacteristic<Inject> injectShell;
	
	/** @construction 初始化构造 **/
	private InjectServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		injectImpl.initialize(injectShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}