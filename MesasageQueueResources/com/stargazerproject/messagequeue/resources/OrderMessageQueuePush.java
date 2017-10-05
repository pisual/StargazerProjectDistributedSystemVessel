package com.stargazerproject.messagequeue.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="orderMessageQueuePush")
@Qualifier("orderMessageQueuePush")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueuePush implements MessageQueuePush<Order>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;

	@Override
	public void put(Optional<List<Order>> orderList) {
		MessageChannel messageChannel = BeanContainer.instance().getBean(Optional.of("toKafka"), MessageChannel.class);
		for(int i=0; i<orderList.get().size(); i++){
			messageChannel.send(new GenericMessage<Order>(orderList.get().get(i)), 200);
		}
	}
	
	@ServiceActivator(inputChannel = "toKafka")
	@Bean
	public MessageHandler handler() throws Exception {
		KafkaProducerMessageHandler<String, String> handler = new KafkaProducerMessageHandler<>(kafkaTemplate());
		handler.setTopicExpression(new LiteralExpression(systemParameter.get(Optional.of("Message_Push_Queue_Topic")).get()));
		//handler.setMessageKeyExpression(new LiteralExpression(this.messageKey));
		return handler;
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, systemParameter.get(Optional.of("Kafka_Binder_Brokers")).get());
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(props);
	}

}
