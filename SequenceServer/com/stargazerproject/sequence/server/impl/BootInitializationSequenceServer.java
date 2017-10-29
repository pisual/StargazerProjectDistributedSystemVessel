package com.stargazerproject.sequence.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name BootInitializationSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的cellsGenerateSequence相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="bootInitializationSequenceServer")
@Qualifier("bootInitializationSequenceServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BootInitializationSequenceServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("bootInitializationSequence")
	private StanderCharacteristicShell<Sequence> sequence;
	
	/** @construction 初始化构造 **/
	private BootInitializationSequenceServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("localLogServerListener", "systemParameterCacheServerListener", "byteArrayCacheServerListener", "nodeNegotiateServerListener");
		Optional<Sequence> sequenceImpl = BeanContainer.instance().getBean(Optional.of("sequenceResourcesCharacteristic"), Optional.class);
		sequenceImpl.get().startSequence(Optional.of("bootInitializationSequence"));
		sequence.initialize(sequenceImpl);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}