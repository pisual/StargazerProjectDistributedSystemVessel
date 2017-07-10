package com.stargazerproject.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class GlobalApplicationContext implements ApplicationContextAware{
	
    protected static ApplicationContext applicationContext;
    
    protected GlobalApplicationContext() {}
    
	@Override
	public void setApplicationContext(ApplicationContext applicationContextArg) throws BeansException {
		applicationContext = applicationContextArg;
	}
	
}