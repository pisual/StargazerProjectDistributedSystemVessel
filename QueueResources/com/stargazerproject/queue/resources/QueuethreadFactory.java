package com.stargazerproject.queue.resources;

import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="queuethreadFactory")
@Qualifier("queuethreadFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public abstract class QueuethreadFactory implements ThreadFactory{
	
	protected QueuethreadFactory() {}
	
	@Override
	public Thread newThread(final Runnable r){
		Thread t = new Thread(r);
		return t;
		}
	
	}
