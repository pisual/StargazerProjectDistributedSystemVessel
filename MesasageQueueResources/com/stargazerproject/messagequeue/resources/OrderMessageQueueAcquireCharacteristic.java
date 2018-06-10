package com.stargazerproject.messagequeue.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.transaction.impl.Order;

@Component(value="orderMessageQueueAcquireCharacteristic")
@Qualifier("orderMessageQueueAcquireCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueAcquireCharacteristic implements MessageQueueAcquire<Order>, BaseCharacteristic<MessageQueueAcquire<Order>>{

	/** @illustrate 消息队列集群地址 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_KafkaBinderBrokers;
	
	/** @illustrate 系统ID  **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_System_CellsID;
	
	/** @illustrate ENABLE_AUTO_COMMIT_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_ENABLE_AUTO_COMMIT_CONFIG;
	
	/** @illustrate AUTO_COMMIT_INTERVAL_MS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_AUTO_COMMIT_INTERVAL_MS_CONFIG;
	
	/** @illustrate SESSION_TIMEOUT_MS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_SESSION_TIMEOUT_MS_CONFIG;
	
	/** @illustrate MAX_POLL_RECORDS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_MAX_POLL_RECORDS_CONFIG;
	
	/** @illustrate KEY_DESERIALIZER_CLASS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_KEY_DESERIALIZER_CLASS_CONFIG;
	
	/** @illustrate VALUE_DESERIALIZER_CLASS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_VALUE_DESERIALIZER_CLASS_CONFIG;
	
	private KafkaConsumer<String, Order> consumer;
	
	public OrderMessageQueueAcquireCharacteristic() {}
	
	@Override
	public Optional<MessageQueueAcquire<Order>> characteristic() {
		receivedInitialization();
		return Optional.of(this);
	}
	
	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		List<Order> resultList = new ArrayList<Order>(messageNumber.get());
		for (int i = 0; i < messageNumber.get(); i++) {
			acquireConsumerRecords(resultList);
		}
		return Optional.of(resultList);
	}
	
	private void acquireConsumerRecords(List<Order> list){
		ConsumerRecords<String, Order> consumerRecords = consumer.poll(1000);
		consumerRecords.forEach(new Consumer<ConsumerRecord<String,Order>>() {
			public void accept(ConsumerRecord<String,Order> t) {
				list.add(t.value());
			};
		});
	}
	
	private void receivedInitialization() {
		consumer = new KafkaConsumer<>(consumeProperties());
	}
	
	private Properties consumeProperties() {
		Properties props = new Properties();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, Kernel_System_CellsID);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Kernel_Queue_OrderMessage_KafkaBinderBrokers);
	    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, Kernel_Queue_OrderMessage_MAX_POLL_RECORDS_CONFIG);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Kernel_Queue_OrderMessage_ENABLE_AUTO_COMMIT_CONFIG);
	    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, Kernel_Queue_OrderMessage_SESSION_TIMEOUT_MS_CONFIG);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Kernel_Queue_OrderMessage_KEY_DESERIALIZER_CLASS_CONFIG);
	    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, Kernel_Queue_OrderMessage_AUTO_COMMIT_INTERVAL_MS_CONFIG);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Kernel_Queue_OrderMessage_VALUE_DESERIALIZER_CLASS_CONFIG);
	    return props;
	}
	
}
