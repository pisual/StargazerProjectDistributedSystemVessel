package com.stargazerproject.messagequeue.resources;

import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.transaction.Transaction;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;

@Component(value="transactionMessageQueueControlCharacteristic")
@Qualifier("transactionMessageQueueControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionMessageQueueControlCharacteristic implements MessageQueueControl<Transaction>, BaseCharacteristic<MessageQueueControl<Transaction>>{
	
	/** @illustrate Kernel_Queue_TransactionMessage_KafkaZookeeperBrokers **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_KafkaZookeeperBrokers;
	
	/** @illustrate 系统ID  **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_System_CellsID;
	
	@Autowired
	@Qualifier("objectParameterCache")
	private Cache<String, String> objectParameterCache;
	
	public TransactionMessageQueueControlCharacteristic() {}
	
	@Override
	public Optional<MessageQueueControl<Transaction>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void join() {
		ZkUtils zkUtils = ZkUtils.apply(Kernel_Queue_TransactionMessage_KafkaZookeeperBrokers, 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.createTopic(zkUtils, Kernel_System_CellsID, 1, 1, new Properties(), RackAwareMode.Enforced$.MODULE$);
		zkUtils.close();
		objectParameterCache.put(Optional.of("Topic"), Optional.of(Kernel_System_CellsID));
	}

	@Override
	public void out() {
		ZkUtils zkUtils = ZkUtils.apply(Kernel_Queue_TransactionMessage_KafkaZookeeperBrokers, 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.deleteTopic(zkUtils, objectParameterCache.get(Optional.of("Topic")).get());
		zkUtils.close();
	}

}
