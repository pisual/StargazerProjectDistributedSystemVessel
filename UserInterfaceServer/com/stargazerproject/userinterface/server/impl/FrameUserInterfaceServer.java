package com.stargazerproject.userinterface.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.base.impl.BaseUserInterface;

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
	@Qualifier("assaultLilysUserInterface")
	private StanderCharacteristicShell<BaseUserInterface> baseUserInterface;
	
	@Autowired
	@Qualifier("assaultLilysUserInterfaceShall")
	private BaseCharacteristic<LoadingUserInterface> loadingUserInterfaceShell;
	
	@Autowired
	@Qualifier("assaultLilysUserInterface")
	private BaseUserInterface userInterface;
	
	/** @construction 初始化构造 **/
	private FrameUserInterfaceServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
	//	assaultLilysUserInterface.initialize(assaultLilysUserInterfaceShall.characteristic());

	//	assaultLilysUserInterfaceShall.characteristic().get().startLoading();
		userInterface.startLoading();	
//		
//		//****样品展示代码Start
//		try {
//			userInterface.startLoading();
//		for(int i=0;i<100;i++)
//		{
//			TimeUnit.MILLISECONDS.sleep(100);
//			userInterface.increaseProgressBar(Optional.of(i+""), Optional.of(i));
//		}
//			TimeUnit.SECONDS.sleep(5);
//			userInterface.endLoading();
//			userInterface.startMain();
//		TimeUnit.SECONDS.sleep(5);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		//****样品展示代码End
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
}