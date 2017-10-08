package com.stargazerproject.userinterface.server.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;

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
	private StanderCharacteristicShell<AssaultLilysUserInterface> zoneNegotiateShell;
	
	/** @construction 初始化构造 **/
	private FrameUserInterfaceServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener");
		Optional<AssaultLilysUserInterface> assaultLilysUserInterface = BeanContainer.instance().getBean(Optional.of("assaultLilysUserInterfaceShallCharacteristic"), Optional.class);
		zoneNegotiateShell.initialize(assaultLilysUserInterface);
		//****样品展示代码Start
		try {
		assaultLilysUserInterface.get().startLoading();
		for(int i=0;i<100;i++)
		{
			TimeUnit.MILLISECONDS.sleep(100);
			assaultLilysUserInterface.get().increaseProgressBar(Optional.of(i+""), Optional.of(i));
		}
			TimeUnit.SECONDS.sleep(5);
		assaultLilysUserInterface.get().endLoading();
		assaultLilysUserInterface.get().startMain();
		TimeUnit.SECONDS.sleep(5);

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