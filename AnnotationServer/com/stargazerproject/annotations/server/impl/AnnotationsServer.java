package com.stargazerproject.annotations.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.Annotations;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="annotationsServer")
@Qualifier("annotationsServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AnnotationsServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("annotationsImpl")
	private StanderCharacteristicShell<Annotations> annotationsImpl;
	
	@Autowired
	@Qualifier("annotationsShell")
	private BaseCharacteristic<Annotations> annotationsShell;
	
	/** @construction 初始化构造 **/
	private AnnotationsServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		annotationsImpl.initialize(annotationsShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}