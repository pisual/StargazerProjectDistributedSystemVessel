package com.stargazerproject.spring.context.initialization.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GlobalAnnotationApplicationContextInitializationTest {
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class,GroupQueueConfiguration.class);
	}
	
	@Test
	public void getBeanTest(){
		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"),Cache.class);
	}
	
}
