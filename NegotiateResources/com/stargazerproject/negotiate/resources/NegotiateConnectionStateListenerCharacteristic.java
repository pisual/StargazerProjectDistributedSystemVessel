package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="negotiateConnectionStateListener")
@Qualifier("negotiateConnectionStateListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateConnectionStateListenerCharacteristic implements ConnectionStateListener,BaseCharacteristic<ConnectionStateListener>{

	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Override
	@Bean(name="negotiateConnectionStateListenerCharacteristic")
	@Lazy(true)
	public Optional<ConnectionStateListener> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void stateChanged(org.apache.curator.framework.CuratorFramework client, org.apache.curator.framework.state.ConnectionState newState) {	
		switch (newState) {
		case LOST:
			  for(int i=0; i<getIntegerParameter("Zookeeeper_Retry_Policy_Retry_Connect_Number"); i++){
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
			  for(int i=0; i<getIntegerParameter("Zookeeeper_Retry_Policy_Retry_Connect_Number"); i++){
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
		return Integer.parseInt(systemParameter.get(Optional.of(key)).get());
	}

}
