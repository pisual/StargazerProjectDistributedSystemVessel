package com.stargazerproject.test;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class Listener {

    public final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "101", topics = "topic1")
    public void listen1(String foo) {
    	System.out.println("收到数据"+foo);
        this.latch1.countDown();
    }

}