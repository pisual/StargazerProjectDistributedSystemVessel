package com.stargazerproject.messagequeue.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.order.impl.Order;

@Component(value="orderMessageQueueAcquire")
@Qualifier("orderMessageQueueAcquire")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueAcquireCharacteristic implements MessageQueueAcquire<Order>, BaseCharacteristic<MessageQueueAcquire<Order>>{

	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	private KafkaConsumer<String, Order> consumer;
	
	public OrderMessageQueueAcquireCharacteristic() {}
	
	@Override
	@Bean(name="orderMessageQueueAcquireCharacteristic")
	@Lazy(true)
	public Optional<MessageQueueAcquire<Order>> characteristic() {
		receivedInitialization();
		return Optional.of(this);
	}
	
	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		List<Order> resultList = new ArrayList<Order>(messageNumber.get());
		for (int i = 0; i < messageNumber.get()%100; i++) {
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
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, systemParameter.get(Optional.of("Kafka_Binder_Brokers")).get());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, systemParameter.get(Optional.of("Cells_UUID")).get());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
	    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
	    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
	    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.BytesDeserializer");
	    return props;
	}
	
}
