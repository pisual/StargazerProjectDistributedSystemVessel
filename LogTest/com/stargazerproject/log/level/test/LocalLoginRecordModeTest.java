package com.stargazerproject.log.level.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupLogConfiguration.class,GroupQueueConfiguration.class,GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupCacheConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class LocalLoginRecordModeTest implements WorkInTest{
	
	@Autowired
	@Qualifier("LocalLog")
	private StanderServiceShell log;
	
	@Autowired
	@Qualifier("localLogServerManage")
	private AbstractIdleService logServer;
	
	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(log);
		logServer.startAsync().awaitRunning();
	}

}
