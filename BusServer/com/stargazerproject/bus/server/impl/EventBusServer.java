package com.stargazerproject.bus.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.bus.Bus;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.transaction.base.impl.BaseEvent;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="eventBusServer")
@Qualifier("eventBusServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("eventBusImpl")
	private StanderCharacteristicShell<Bus<BaseEvent>> eventBus;
	
	@Autowired
	@Qualifier("eventBusResourcesShell")
	private BaseCharacteristic<Bus<BaseEvent>> eventBusResourcesShell;
	
	/** @construction 初始化构造 **/
	private EventBusServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		eventBus.initialize(eventBusResourcesShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}