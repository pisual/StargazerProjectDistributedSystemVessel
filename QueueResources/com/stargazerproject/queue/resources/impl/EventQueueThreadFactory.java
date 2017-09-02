package com.stargazerproject.queue.disruptor.resources.threadfactory.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.queue.disruptor.resources.threadfactory.base.impl.QueuethreadFactory;

@Component
@Qualifier("orderQueueThreadFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderQueueThreadFactory extends QueuethreadFactory{
}
