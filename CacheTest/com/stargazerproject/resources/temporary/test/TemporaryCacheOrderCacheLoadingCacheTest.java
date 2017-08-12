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
import com.google.common.cache.LoadingCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.OrderCacheCacheLoaderCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheLoadingCacheCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheRemovalListenerCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SystemParameterCahce.class, SystemParameterCahceShell.class,StargazerProjectParameterList.class,OrderCacheLoadingCacheCharacteristic.class,OrderCacheCacheLoaderCharacteristic.class,OrderCacheRemovalListenerCharacteristic.class,GroupLogConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class TemporaryCacheOrderCacheLoadingCacheTest implements WorkInTest{
	
	@Autowired
	@Qualifier("temporaryCacheOrderCacheLoadingCache")
	private BaseCharacteristic<LoadingCache<String,Order>> cacheLoader;
	
	@Autowired
	@Qualifier("temporaryCacheOrderCacheLoadingCacheCharacteristic")
	private Optional<LoadingCache<String, Order>> loadingCache;
	
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(cacheLoader);
		System.out.println(loadingCache);
	}
	
}
