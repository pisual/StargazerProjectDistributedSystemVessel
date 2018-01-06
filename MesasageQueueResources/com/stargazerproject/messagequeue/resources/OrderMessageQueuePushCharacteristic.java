package com.stargazerproject.messagequeue.resources;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;

@Component(value="orderMessageQueuePushCharacteristic")
@Qualifier("orderMessageQueuePushCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueuePushCharacteristic implements MessageQueuePush<Order>, BaseCharacteristic<MessageQueuePush<Order>>{
	
	/** @illustrate 系统ID  **/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_System_CellsID;
	
	/** @illustrate 消息队列集群地址 **/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Queue_OrderMessage_KafkaBinderBrokers;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Autowired
	@Qualifier("orderMessageQueueCallBack")
	private Callback orderMessageQueueCallBack;
	
	private KafkaProducer<String, Order> producer;
	
	public OrderMessageQueuePushCharacteristic() {}
	
	@Override
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
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Kernel_Queue_OrderMessage_KafkaBinderBrokers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, Kernel_System_CellsID);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.BytesSerializer");
		return props;
	}

}
