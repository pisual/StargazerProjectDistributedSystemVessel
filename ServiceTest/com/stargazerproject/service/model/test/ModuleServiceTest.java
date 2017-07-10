package com.stargazerproject.service.model.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.test.pattern.WorkInTest;

public class ModuleServiceTest implements WorkInTest{

	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(GroupQueueConfiguration.class,GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class);
	}

	@Override
	@Test
	public void interfaceInitialize() {
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

}
