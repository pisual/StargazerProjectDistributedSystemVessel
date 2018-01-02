package com.stargazerproject.negotiate.resources;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="negotiateRetryPolicyCharacteristic")
@Qualifier("negotiateRetryPolicyCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateRetryPolicyCharacteristic implements BaseCharacteristic<RetryPolicy>{
	
	/**@illustrate 重新连接策略尝试数目**/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_Connection_RetryPolicySleepTime;
	
	/**@illustrate 重新连接策略尝试次数**/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_Connection_RetryConnectTime;
	
	private RetryPolicy retryPolicy;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateRetryPolicyCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateRetryPolicyCharacteristic(Optional<String> Kernel_Negotiate_Connection_RetryPolicySleepTimeArg,
											 Optional<String> Kernel_Negotiate_Connection_RetryConnectTimeArg) {
		Kernel_Negotiate_Connection_RetryPolicySleepTime = Kernel_Negotiate_Connection_RetryPolicySleepTimeArg.get();
		Kernel_Negotiate_Connection_RetryConnectTime = Kernel_Negotiate_Connection_RetryConnectTimeArg.get();
	}

	@Override
	public Optional<RetryPolicy> characteristic() {
		retryPolicyInitialization();
		return Optional.of(retryPolicy);
	}
	
	private void retryPolicyInitialization(){
		 retryPolicy = new ExponentialBackoffRetry(getIntegerParameter(Kernel_Negotiate_Connection_RetryPolicySleepTime), getIntegerParameter(Kernel_Negotiate_Connection_RetryConnectTime));
	}

	private Integer getIntegerParameter(String key){
		return Integer.parseInt(key);
	}
}
