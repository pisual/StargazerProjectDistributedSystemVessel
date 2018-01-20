package com.stargazerproject.information.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.information.Information;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="cellsInformationServer")
@Qualifier("cellsInformationServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformationServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("cellsInformation")
	private StanderCharacteristicShell<Information> cellsInformation;
	
	@Autowired
	@Qualifier("cellsInformationShell")
	private BaseCharacteristic<Information> cellsInformationShell;
	
	/** @construction 初始化构造 **/
	private CellsInformationServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		cellsInformation.initialize(cellsInformationShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}