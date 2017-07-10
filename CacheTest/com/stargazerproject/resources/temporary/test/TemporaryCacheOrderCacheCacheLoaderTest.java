package com.stargazerproject.resources.temporary.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.cache.CacheLoader;
import com.stargazerproject.cache.resources.temporarycache.cacheLoader.TemporaryCacheOrderCacheCacheLoader;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TemporaryCacheOrderCacheCacheLoader.class,GroupLogConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TemporaryCacheOrderCacheCacheLoaderTest implements WorkInTest{
	
	@Autowired
	@Qualifier("temporaryCacheOrderCacheCacheLoader")
	private BaseCharacteristic<CacheLoader<String, Order>> cacheLoader;
	
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(cacheLoader);
	}
	
}
