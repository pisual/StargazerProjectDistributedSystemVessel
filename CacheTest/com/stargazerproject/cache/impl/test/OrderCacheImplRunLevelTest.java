package com.stargazerproject.cache.impl.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.test.pattern.WorkInTest;

public class OrderCacheImplRunLevelTest implements WorkInTest{
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class);
	}
	
	@Test
	@Override
	public void interfaceInitialize() {}
	
	@Test
	public void serverNotStartPutObject(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"), ServiceControl.class);
		serviceControl.startAllservice();
		Cache<String,Order> systemParameter = BeanContainer.instance().getBean(Optional.of("orderCache"), Cache.class);
	}
}