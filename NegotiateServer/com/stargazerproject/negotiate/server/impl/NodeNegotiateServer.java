package com.stargazerproject.negotiate.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;

/** 
 *  @name nodenNegotiate服务的实现
 *  @illustrate 继承于ServiceShell的nodenNegotiate相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="nodeNegotiateServer")
@Qualifier("nodeNegotiateServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NodeNegotiateServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private StanderCharacteristicShell<Negotiate> nodeNegotiate;
	
	@Autowired
	@Qualifier("nodenNegotiateShell")
	private BaseCharacteristic<Negotiate> nodenNegotiateShell;
	
	/** @construction 初始化构造 **/
	private NodeNegotiateServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener", "bigCacheIndexCacheServerListener", "byteArrayCacheServerListener");
		Optional<Negotiate> nodeNegotiateResources = nodenNegotiateShell.characteristic();
		nodeNegotiateResources.get().start();
		nodeNegotiate.initialize(nodeNegotiateResources);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}