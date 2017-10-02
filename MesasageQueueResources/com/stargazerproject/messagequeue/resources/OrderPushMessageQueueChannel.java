package com.stargazerproject.messagequeue.resources;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderPushMessageQueueChannel {
	
	String OUTPUT = "OrderPushMessageQueueChannel";
	
    @Output(OUTPUT)
    MessageChannel output();
}
