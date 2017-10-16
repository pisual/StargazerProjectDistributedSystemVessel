package com.stargazerproject.messagequeue.resources;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
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
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;

@Component(value="orderMessageQueuePush")
@Qualifier("orderMessageQueuePush")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueuePushCharacteristic implements MessageQueuePush<Order>, BaseCharacteristic<MessageQueuePush<Order>>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Autowired
	@Qualifier("orderMessageQueueCallBack")
	private Callback orderMessageQueueCallBack;
	
	private KafkaProducer<String, Order> producer;
	
	public OrderMessageQueuePushCharacteristic() {}
	
	@Override
	@Bean(name="orderMessageQueuePushCharacteristic")
	@Lazy(true)
	public Optional<MessageQueuePush<Order>> characteristic() {
		producerInitialization();
		return Optional.of(this);
	}

	@Override
	public void put(Optional<List<Order>> orderList) {
		orderList.get().parallelStream().forEach(e -> producer.send(new ProducerRecord<>(systemParameter.get(Optional.of("Message_Push_Queue_Topic")).get(), e.IDSequence().get(), e), orderMessageQueueCallBack));
	}

	private void producerInitialization() {
		producer = new KafkaProducer<String, Order>(producerProperties());
	}
	
	private Properties producerProperties() {
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, systemParameter.get(Optional.of("Kafka_Binder_Brokers")).get());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, systemParameter.get(Optional.of("Cells_UUID")).get());
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.BytesSerializer");
		return props;
	}

}
