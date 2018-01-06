package com.stargazerproject.queue.resources;

import java.util.concurrent.ThreadFactory;

public abstract class QueuethreadFactory implements ThreadFactory{
	
	protected QueuethreadFactory() {}
	
	@Override
	public Thread newThread(final Runnable r){
		Thread t = new Thread(r);
		return t;
		}
	
	}
