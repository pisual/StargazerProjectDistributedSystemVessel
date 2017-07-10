package com.stargazerproject.cache.impl.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class OrderCacheImplTest implements WorkInTest{
	
	@Autowired
	@Qualifier("orderCache")
	private StanderCharacteristicShell<LoadingCache<String,Order>> cache;
	
	@Autowired
	@Qualifier("orderCache")
	private Cache<String,Order> systemCache;
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(cache);
	}
	
}
