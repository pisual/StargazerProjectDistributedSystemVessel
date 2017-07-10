package com.stargazerproject.queue.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.stargazerproject.queue.di.configuration.BaseQueueDIConfiguration;

@Configuration 
@Import({BaseQueueDIConfiguration.class})
public class GroupQueueConfiguration {

}
