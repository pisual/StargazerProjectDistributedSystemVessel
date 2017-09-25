package com.stargazerproject.cache.impl.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class BigCacheIndexCahceModuleServiceTest{
	
	public static Cache<String, Map<String, Integer>> cache;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  

	@Test
	public void SpringInit(){
		String args[] = {"debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

	@Test
	public void getCacheTest(){
		cache = BeanContainer.instance().getBean(Optional.of("bigCacheIndexCahce"), Cache.class);
	}
	
	@Test(timeout=10)
	public void cachePutTest(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("TestKey", 1);
		cache.put(Optional.of("TestKey"), Optional.of(map));
	}
	
	@Test
	public void cacheGetTest(){
		System.out.println(cache.get(Optional.of("TestKey")).get());
	}
	
	@Test
	public void cacheRemoveTest(){
		cache.remove(Optional.of("TestKey"));
	}
	
	@Test
	public void cacheRemoveLatersTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Optional.get() cannot be called on an absent value");  
		System.out.println(cache.get(Optional.of("TestKey")).get());
	}
	
	@Test(timeout=10000)
	public void cacheBitchPutTest(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("TestKey", 1);
		for (int i = 0; i < 1000000; i++) {
			cache.put(Optional.of("TestKey" + i), Optional.of(map));
		}
		System.out.println("百万级测试Put完毕");
	}
	
	@Test(timeout=10000)
	public void cacheBitchGetTest(){
		for (int i = 0; i < 1000000; i++) {
			cache.get(Optional.of("TestKey" + i));
		}
		System.out.println("百万级测试Get完毕");
	}
	
	@Test
	public void serviceStopTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.stopAllService();
	}
	
	@Test
	public void serviceStopLaterGetTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Server Not Start");  
		cache.get(Optional.of("TestKey"));
	}
	
	@Test
	public void serviceStopLaterRemoveTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Server Not Start");  
		cache.remove(Optional.of("TestKey"));
	}
	
	@Test
	public void serviceStopLaterPutTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Server Not Start");  
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("TestKey", 1);
		cache.put(Optional.of("TestKey"), Optional.of(map));
	}

	@Test
	public void serviceStartAgainTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Service LocalLogServerManage [TERMINATED] is TERMINATED, cannot start it.");  
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
	
	@Test
	public void serviceStopAgainTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.stopAllService();
	}
	
}
