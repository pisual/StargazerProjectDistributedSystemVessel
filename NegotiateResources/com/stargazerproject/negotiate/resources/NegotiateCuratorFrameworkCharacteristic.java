package com.stargazerproject.negotiate.resources;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="negotiateCuratorFrameworkCharacteristic")
@Qualifier("negotiateCuratorFrameworkCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateCuratorFrameworkCharacteristic implements BaseCharacteristic<CuratorFramework>{
	
	/** @name Zookeeper主机 **/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_Connection_Host;
	
	@Autowired
	@Qualifier("negotiateRetryPolicyCharacteristic")
	private BaseCharacteristic<RetryPolicy> negotiateRetryPolicyCharacteristic;
	
	@Autowired
	@Qualifier("negotiateConnectionStateListenerCharacteristic")
	private BaseCharacteristic<ConnectionStateListener> negotiateConnectionStateListenerCharacteristic;
	
	protected CuratorFramework curatorFramework;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateCuratorFrameworkCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateCuratorFrameworkCharacteristic(Optional<BaseCharacteristic<RetryPolicy>> negotiateRetryPolicyCharacteristicArg, 
			                                       Optional<BaseCharacteristic<ConnectionStateListener>> negotiateConnectionStateListenerCharacteristicArg, 
			                                       Optional<String> Kernel_Negotiate_Connection_HostArg) {
		Kernel_Negotiate_Connection_Host = Kernel_Negotiate_Connection_HostArg.get();
		negotiateRetryPolicyCharacteristic = negotiateRetryPolicyCharacteristicArg.get();
		negotiateConnectionStateListenerCharacteristic = negotiateConnectionStateListenerCharacteristicArg.get();
	}
	
	@Override
	public Optional<CuratorFramework> characteristic() {
		curatorFrameworkInitialization();
		return Optional.of(curatorFramework);
	}
	
	private void curatorFrameworkInitialization(){
		curatorFramework = CuratorFrameworkFactory.newClient(Kernel_Negotiate_Connection_Host, negotiateRetryPolicyCharacteristic.characteristic().get());
		curatorFramework.getConnectionStateListenable().addListener(negotiateConnectionStateListenerCharacteristic.characteristic().get());
	}
}
