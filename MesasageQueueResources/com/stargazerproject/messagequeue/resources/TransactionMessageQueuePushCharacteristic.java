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
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionMessageQueuePushCharacteristic")
@Qualifier("transactionMessageQueuePushCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionMessageQueuePushCharacteristic implements MessageQueuePush<Transaction>, BaseCharacteristic<MessageQueuePush<Transaction>>{
	
	/** @illustrate 系统ID  **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_System_CellsID;
	
	/** @illustrate 消息队列集群地址 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_KafkaBinderBrokers;
	
	/** @illustrate KEY_SERIALIZER_CLASS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_KEY_SERIALIZER_CLASS_CONFIG;
	
	/** @illustrate VALUE_SERIALIZER_CLASS_CONFIG **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_VALUE_SERIALIZER_CLASS_CONFIG;
	
	/** @illustrate Kernel_Queue_TransactionMessage_MessagePushQueueTopic 消息推送的目的Topic **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_MessagePushQueueTopic;
	
	/** @illustrate Kernel_Queue_TransactionMessage_MessagePushQueueTopic 消息推送的目的Partition **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_TransactionMessage_MessagePushQueuePartition;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Autowired
	@Qualifier("transactionMessageQueueCallBackCharacteristic")
	private Callback TransactionMessageQueueCallBack;
	
	private KafkaProducer<String, Transaction> producer;
	
	public TransactionMessageQueuePushCharacteristic() {}
	
	@Override
	public Optional<MessageQueuePush<Transaction>> characteristic() {
		producerInitialization();
		return Optional.of(this);
	}

	@Override
	public void put(Optional<List<Transaction>> orderList) {
		orderList.get().parallelStream().forEach(e -> producer.send(new ProducerRecord<>(Kernel_Queue_TransactionMessage_MessagePushQueueTopic, 
				                                                                         Integer.parseInt(Kernel_Queue_TransactionMessage_MessagePushQueuePartition), 
				                                                                         null,
				                                                                         null), 
				                                                    TransactionMessageQueueCallBack));
	}

	private void producerInitialization() {
		producer = new KafkaProducer<String, Transaction>(producerProperties());
	}
	
	private Properties producerProperties() {
		Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, Kernel_System_CellsID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Kernel_Queue_TransactionMessage_KafkaBinderBrokers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Kernel_Queue_TransactionMessage_KEY_SERIALIZER_CLASS_CONFIG);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Kernel_Queue_TransactionMessage_VALUE_SERIALIZER_CLASS_CONFIG);
		return props;
	}

}
