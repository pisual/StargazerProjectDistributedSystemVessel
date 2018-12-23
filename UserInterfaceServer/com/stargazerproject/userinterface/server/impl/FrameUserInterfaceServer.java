package com.stargazerproject.userinterface.server.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.userinterface.UserInterface;

/** 
 *  @name frameUserInterface服务的实现
 *  @illustrate 继承于ServiceShell的frameUserInterface相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="frameUserInterfaceServer")
@Qualifier("frameUserInterfaceServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FrameUserInterfaceServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("userInterfaceImpl")
	private StanderCharacteristicShell<UserInterface> userInterface;
	
	@Autowired
	@Qualifier("frameShell")
	private BaseCharacteristic<UserInterface> userInterfaceShall;
	
	@Autowired
	@Qualifier("userInterfaceImpl")
	private UserInterface userInterfaceframe;
	
	/** @construction 初始化构造 **/
	private FrameUserInterfaceServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		userInterface.initialize(userInterfaceShall.characteristic());
		
		//****样品展示代码Start
		try {
			userInterfaceframe.startLoading();
		for(int i=0;i<100;i++)
		{
			TimeUnit.MILLISECONDS.sleep(10);
			userInterfaceframe.increaseProgressBar(Optional.of(i+""), Optional.of(i));
		}
			userInterfaceframe.startMain();
			userInterfaceframe.endLoading();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//****样品展示代码End
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}