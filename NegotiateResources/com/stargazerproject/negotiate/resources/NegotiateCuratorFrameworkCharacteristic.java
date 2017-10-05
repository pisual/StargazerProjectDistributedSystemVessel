package com.stargazerproject.negotiate.resources;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
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
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateCuratorFramework")
@Qualifier("negotiateRetrnegotiateCuratorFrameworkyPolicy")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateCuratorFrameworkCharacteristic implements BaseCharacteristic<CuratorFramework>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	protected CuratorFramework curatorFramework;
	private Optional<RetryPolicy> retryPolicy;
	private Optional<ConnectionStateListener> connectionStateListener;
	
	@Override
	@Bean(name="negotiateCuratorFrameworkCharacteristic")
	@Lazy(true)
	public Optional<CuratorFramework> characteristic() {
		retryPolicy = BeanContainer.instance().getBean(Optional.of("negotiateRetryPolicyCharacteristic"), Optional.class);
		connectionStateListener = BeanContainer.instance().getBean(Optional.of("negotiateConnectionStateListenerCharacteristic"), Optional.class);
		curatorFrameworkInitialization();
		return Optional.of(curatorFramework);
	}
	
	private void curatorFrameworkInitialization(){
		curatorFramework = CuratorFrameworkFactory.newClient(systemParameter.get(Optional.of("Zookeeeper_Connect_Host")).get(), retryPolicy.get());
		curatorFramework.getConnectionStateListenable().addListener(connectionStateListener.get());
	}
}
