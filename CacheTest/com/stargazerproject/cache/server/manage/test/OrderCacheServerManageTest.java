package com.stargazerproject.cache.server.manage.test;

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
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class OrderCacheServerManageTest implements WorkInTest{
	
	@Autowired
	@Qualifier("orderCacheServerManage")
	private AbstractIdleService abstractIdleService;
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(abstractIdleService);
	}

}
