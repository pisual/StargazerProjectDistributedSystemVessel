package com.stargazerproject.log.level.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.test.pattern.WorkInTest;

public class OnlineLoginRecordModeTest implements WorkInTest{
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(GroupLogConfiguration.class,GroupQueueConfiguration.class,GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupCacheConfiguration.class);
	}
	
	@Override
	@Test
	public void interfaceInitialize() {
		AbstractIdleService localServer = BeanContainer.instance().getBean(Optional.of("localLogServerManage"), AbstractIdleService.class);
		localServer.startAsync().awaitRunning();
		AbstractIdleService onlineServer = BeanContainer.instance().getBean(Optional.of("onlineLogServerManage"), AbstractIdleService.class);
		onlineServer.startAsync().awaitRunning();
		onlineServer.stopAsync().awaitTerminated();
	}

}
