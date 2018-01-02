package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateControl;

@Component(value="negotiateControlCharacteristic")
@Qualifier("negotiateControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateControlCharacteristic implements NegotiateControl, BaseCharacteristic<NegotiateControl>{
	
	@Autowired
	@Qualifier("negotiateCuratorFrameworkCharacteristic")
	private BaseCharacteristic<CuratorFramework> negotiateCuratorFrameworkCharacteristic;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateControlCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateControlCharacteristic(Optional<BaseCharacteristic<CuratorFramework>> negotiateCuratorFrameworkArg) {
		negotiateCuratorFrameworkCharacteristic = negotiateCuratorFrameworkArg.get();
	}
	
	@Override
	public Optional<NegotiateControl> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void start() {
		negotiateCuratorFrameworkCharacteristic.characteristic().get().start();
	}

	@Override
	public void close() {
		negotiateCuratorFrameworkCharacteristic.characteristic().get().close();
	}

}
