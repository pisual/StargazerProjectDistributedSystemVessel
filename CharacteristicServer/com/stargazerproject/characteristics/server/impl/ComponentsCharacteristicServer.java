package com.stargazerproject.characteristics.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.characteristics.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="componentsCharacteristicServer")
@Qualifier("componentsCharacteristicServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComponentsCharacteristicServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("componentsCharacteristic")
	private StanderCharacteristicShell<Characteristic> componentsCharacteristic;
	
	@Autowired
	@Qualifier("componentsCharacteristicShell")
	private BaseCharacteristic<Characteristic> componentsCharacteristicShell;
	
	/** @construction 初始化构造 **/
	private ComponentsCharacteristicServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		componentsCharacteristic.initialize(componentsCharacteristicShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}