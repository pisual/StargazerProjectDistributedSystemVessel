package com.stargazerproject.messagequeue.resources;

import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.ObjectParameterCache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.order.impl.Order;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;

@Component(value="orderMessageQueueControl")
@Qualifier("orderMessageQueueControl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueControlCharacteristic implements MessageQueueControl<Order>, BaseCharacteristic<MessageQueueControl<Order>>{
 
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Autowired
	@Qualifier("objectParameterCache")
	private Cache<String, String> objectParameterCache;
	
	private ZkUtils zkUtils;
	
	public OrderMessageQueueControlCharacteristic() {}
	
	@Override
	@Bean(name="orderMessageQueueControlCharacteristic")
	@Lazy(true)
	public Optional<MessageQueueControl<Order>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void join() {
		zkUtils = ZkUtils.apply(systemParameter.get(Optional.of("Kafka_Zookeeper_Brokers")).get(), 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.createTopic(zkUtils, systemParameter.get(Optional.of("Cells_UUID")).get(), 1, 1, new Properties(), RackAwareMode.Enforced$.MODULE$);
		zkUtils.close();
		objectParameterCache.put(Optional.of("Topic"), systemParameter.get(Optional.of("Cells_UUID")));
	}

	@Override
	public void out() {
		zkUtils = ZkUtils.apply(systemParameter.get(Optional.of("Kafka_Zookeeper_Brokers")).get(), 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.deleteTopic(zkUtils, objectParameterCache.get(Optional.of("Topic")).get());
		zkUtils.close();
	}

}
