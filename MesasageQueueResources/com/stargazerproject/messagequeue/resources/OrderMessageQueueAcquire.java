package com.stargazerproject.messagequeue.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.TopicPartitionInitialOffset;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="orderMessageQueueAcquire")
@Qualifier("orderMessageQueueAcquire")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueAcquire implements MessageQueueAcquire<Order>{

	/**
	* @name systemParameter
	* @illustrate 缓存特征
	* **/
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Override
	public Optional<List<Order>> get(Optional<Integer> messageNumber) {
		PollableChannel pollableChannel = BeanContainer.instance().getBean(Optional.of("received"), PollableChannel.class);
		List<Order> orderList = new ArrayList<Order>(messageNumber.get());
		for(int i=0; i<orderList.size(); i++){
			orderList.add((Order)pollableChannel.receive(200).getPayload());
		}
		return null;
	}
	
	@Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, systemParameter.get(Optional.of("Kafka_Binder_Brokers")).get());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, systemParameter.get(Optional.of("Message_Acquire_Queue_Topic_Group")).get());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}
	
	@Bean
	public KafkaMessageListenerContainer<String, String> container() throws Exception {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener", "localLogServerListener");
		return new KafkaMessageListenerContainer<>(consumerFactory(), new ContainerProperties(new TopicPartitionInitialOffset(systemParameter.get(Optional.of("Message_Acquire_Queue_Topic")).get(), 0)));
	}

	@Bean
	public KafkaMessageDrivenChannelAdapter<String, String> adapter(KafkaMessageListenerContainer<String, String> container) {
		KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageDrivenChannelAdapter = new KafkaMessageDrivenChannelAdapter<>(container);
		kafkaMessageDrivenChannelAdapter.setOutputChannel(received());
		return kafkaMessageDrivenChannelAdapter;
	}

	@Bean
	public PollableChannel received() {
		return new QueueChannel();
	}
	
}
