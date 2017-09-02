package com.stargazerproject.queue.resources.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.resources.QueuethreadFactory;

@Component
@Qualifier("eventQueueThreadFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventQueueThreadFactory extends QueuethreadFactory{
}
