package com.stargazerproject.sequence.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.base.impl.BaseEvent;

/** 
 *  @name BootInitializationSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的BootInitializationSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="bootInitializationSequenceServer")
@Qualifier("bootInitializationSequenceServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BootInitializationSequenceServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("bootInitializationSequence")
	private StanderCharacteristicShell<Sequence<BaseEvent>> sequence;
	
	/** @construction 初始化构造 **/
	private BootInitializationSequenceServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
//		Optional<Sequence<BaseEvent>> sequenceImpl = BeanContainer.instance().getBean(Optional.of("sequenceResourcesCharacteristic"), Optional.class);
//		try {
//			sequenceImpl.get().startSequence(Optional.of("bootInitializationSequence"));
//		} catch (BusEventTimeoutException e) {
//			e.printStackTrace();
//		}
//		sequence.initialize(sequenceImpl);
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}