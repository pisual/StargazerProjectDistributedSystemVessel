package com.stargazerproject.negotiate.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name zoneNegotiate服务的实现
 *  @illustrate 继承于ServiceShell的zoneNegotiate相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="zoneNegotiateServer")
@Qualifier("zoneNegotiateServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ZoneNegotiateServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("zoneNegotiate")
	private StanderCharacteristicShell<Negotiate> zoneNegotiateShell;
	
	/** @construction 初始化构造 **/
	private ZoneNegotiateServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener", "bigCacheIndexCacheServerListener");
		Optional<Negotiate> zoneNegotiate = BeanContainer.instance().getBean(Optional.of("zoneNegotiateInitialize"), Optional.class);
		zoneNegotiateShell.initialize(zoneNegotiate);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}