package com.stargazerproject.cache.impl.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.aop.configuration.CacheAOPConfiguration;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.SystemParameterBuiltInCacheServer;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.resources.service.ServiceParameterList;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.test.pattern.WorkInTest;

public class SystemParameterCahceModuleServiceTest implements WorkInTest{

	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
				/**Itself Configuration Class**/
				SystemParameterCahce.class,
				SystemParameterCahceCharacteristic.class,
				SystemParameterCahceShell.class,
				SystemParameterBuiltInCacheServer.class,
				SystemParameterCacheServerListener.class,
				SystemParameterCacheServerManage.class,
				CacheAOPConfiguration.class,
				
				/**Depend Configuration Class**/
				StargazerProjectParameterList.class,
				GroupLogConfiguration.class,
				GroupServiceConfiguration.class,
				ServiceParameterList.class
				);
	}

	@Override
	@Test
	public void interfaceInitialize() {
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
		
		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		cache.put(Optional.of("TestKey"), Optional.of("TestValue"));
	}

}
