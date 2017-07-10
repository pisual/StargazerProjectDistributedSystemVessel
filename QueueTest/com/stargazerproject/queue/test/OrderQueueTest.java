package com.stargazerproject.queue.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Optional;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.queue.QueueProduce;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class,GroupQueueConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class OrderQueueTest implements WorkInTest{
	
	@Autowired
	@Qualifier("orderQueue")
	private QueueProduce<Order> queueProduce;
	
	@Autowired
	@Qualifier("orderQueue")
	private QueueControl<Order> queueControl;
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}

	@Override
	@Test
	public void interfaceInitialize() {
		queueControl.start();
		Order order = new Order();
		queueProduce.onData(Optional.of(order));
	}

}
