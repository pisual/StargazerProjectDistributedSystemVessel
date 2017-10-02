package com.stargazerproject.messagequeue.resources;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface OrderAcquireMessageQueueChannel {
	
	String Input = "OrderAcquireMessageQueue";

    @Input(Input)
    MessageChannel Input();
}
