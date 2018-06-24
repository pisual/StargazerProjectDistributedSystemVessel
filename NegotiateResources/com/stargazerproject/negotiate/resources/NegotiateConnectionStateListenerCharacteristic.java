package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="negotiateConnectionStateListenerCharacteristic")
@Qualifier("negotiateConnectionStateListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateConnectionStateListenerCharacteristic implements ConnectionStateListener, BaseCharacteristic<ConnectionStateListener>{
	
	/**@illustrate 重新连接策略尝试数目**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_Connection_RetryConnectTime;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateConnectionStateListenerCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateConnectionStateListenerCharacteristic(Optional<String> Kernel_Negotiate_Connection_RetryConnectTimeArg) {
		Kernel_Negotiate_Connection_RetryConnectTime = Kernel_Negotiate_Connection_RetryConnectTimeArg.get();
	}
	
	@Override
	public Optional<ConnectionStateListener> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void stateChanged(org.apache.curator.framework.CuratorFramework client, org.apache.curator.framework.state.ConnectionState newState) {	
		switch (newState) {
		case LOST:
			  for(int i=0; i<getIntegerParameter(Kernel_Negotiate_Connection_RetryConnectTime); i++){
			  try {
				  Boolean status = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
				  if(status){
						break;
					}
					else{

						continue;
					}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  }
		case SUSPENDED:
			  for(int i=0; i<getIntegerParameter(Kernel_Negotiate_Connection_RetryConnectTime); i++){
			  try {
				Boolean status = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
				if(status){
					break;
				}
				else{
					System.out.println("连接没有成功，将重试");
					continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  }
			break;
		default:
			break;
		}
	};
	
	private Integer getIntegerParameter(String key){
		return Integer.parseInt(key);
	}

}
