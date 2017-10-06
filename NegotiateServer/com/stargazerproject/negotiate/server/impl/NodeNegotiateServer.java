package com.stargazerproject.negotiate.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

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
	@Qualifier("nodeNegotiate")
	private StanderCharacteristicShell<Negotiate> nodeNegotiateShell;
	
	/** @construction 初始化构造 **/
	private NodeNegotiateServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener", "bigCacheIndexCacheServerListener");
		Optional<Negotiate> nodeNegotiate = BeanContainer.instance().getBean(Optional.of("nodenNegotiateShell"), Optional.class);
		nodeNegotiateShell.initialize(nodeNegotiate);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}