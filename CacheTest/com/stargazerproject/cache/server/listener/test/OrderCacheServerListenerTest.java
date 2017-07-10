package com.stargazerproject.cache.server.listener.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stargazerproject.cache.server.listener.impl.OrderCacheServerListener;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.service.WorkInServiceControl;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={OrderCacheServerListener.class,GroupLogConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class OrderCacheServerListenerTest implements WorkInTest{
	
	@Autowired
	@Qualifier("orderCacheServerListener")
	private OrderCacheServerListener orderCacheServerListener;
	
	@Autowired
	@Qualifier("orderCacheServerListener")
	private WorkInServiceState workInServiceState;
	
	@Autowired
	@Qualifier("orderCacheServerListener")
	private WorkInServiceControl workInServiceControl;
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(orderCacheServerListener);
		System.out.println(workInServiceState);
		System.out.println(workInServiceControl);
	}

}
