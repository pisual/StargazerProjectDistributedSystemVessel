package com.stargazerproject.messagequeue.resources;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="orderMessageQueueCallBackCharacteristic")
@Qualifier("orderMessageQueueCallBackCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderMessageQueueCallBackCharacteristic implements Callback{
	
	public OrderMessageQueueCallBackCharacteristic() {}

	public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            System.out.println(
                "message sent to partition(" + metadata.partition() + "), " + "offset(" + metadata.offset() + ")");
        } else {
            exception.printStackTrace();
        }
    }
	
}
