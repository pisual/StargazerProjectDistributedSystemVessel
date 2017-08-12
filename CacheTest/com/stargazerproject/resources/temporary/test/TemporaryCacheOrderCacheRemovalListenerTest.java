package com.stargazerproject.resources.temporary.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Optional;
import com.google.common.cache.RemovalListener;
import com.stargazerproject.cache.impl.resources.OrderCacheRemovalListenerCharacteristic;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={OrderCacheRemovalListenerCharacteristic.class, GroupLogConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TemporaryCacheOrderCacheRemovalListenerTest implements WorkInTest{
	
	@Autowired
	@Qualifier("temporaryCacheOrderCacheRemovalListener")
	private BaseCharacteristic<RemovalListener<String, Order>> removalListener;
	
	@Autowired
	@Qualifier("temporaryCacheOrderCacheRemovalListenerCharacteristic")
	private Optional<RemovalListener<String, Order>> removalListenerCharacteristic;
	
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(removalListener);
	}
	
}