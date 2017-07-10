package com.stargazerproject.test;


import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.spi.ProducerFactory;

import kafka.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.config.ContainerProperties;

import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

@Configuration
public class Kafka {
	
	@Autowired
	public KafkaTemplate<Integer, String> template;
	
    private static final String TEMPLATE_TOPIC = "templateTopic";
	
	 ContainerProperties containerProperties = new ContainerProperties(TEMPLATE_TOPIC);
	 
	public Kafka() {
//	    ListenableFuture<SendResult<Integer, String>> future = template.send("annotated1", 0, "foo");
//	    ContainerProperties containerProps = new ContainerProperties("topic1", "topic2");
//	    final CountDownLatch latch = new CountDownLatch(4);
//	    containerProps.setMessageListener(new MessageListener<Integer, String>() {
//
//	        @Override
//	        public void onMessage(ConsumerRecord<Integer, String> message) {
//	            latch.countDown();
//	        }
//
//	    });
//	    KafkaMessageListenerContainer<Integer, String> container = createContainer(containerProps);
//	    container.setBeanName("testAuto");
//	    container.start();
	    
	    
	}

	@Bean
	public ProducerFactory<Integer, String> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    return props;
	}
	
	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate() {
	    return new KafkaTemplate<Integer, String>(producerFactory());
	}
	
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<Integer, String>
//                        kafkaListenerContainerFactory() {
//    	ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
//                                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
    
//    @Bean
//    public ConsumerFactory<Integer, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//    
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        return props;
//    }
    
    
//	@Bean
//	public KafkaMessageListenerContainer<String, String> container(ConsumerFactory<String, String> kafkaConsumerFactory) {
//		return new KafkaMessageListenerContainer(kafkaConsumerFactory, containerProperties);
//	}
    
//    @Bean
//    public Listener listener() {
//        return new Listener();
//    }
    
	public static void main(String[] args) {
		
//		Kafka k = new Kafka();
//		k.template.send("annotated1", 0, "foo");
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(Kafka.class);
	}
    
}
