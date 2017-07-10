package com.stargazerproject.cache.impl.test;

import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.EventCache;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={EventCache.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class EventCacheImplTest implements WorkInTest{
	
	@Autowired
	@Qualifier("eventCache")
	private StanderCharacteristicShell<Map<String,String>> cacheShell;
	
	@Autowired
	@Qualifier("eventCache")
	private Cache<String,String> cache;
	
	static{
		System.setProperty("spring.profiles.active","dev,debugLogLevel");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(cacheShell);
		System.out.println(cache);
	}
	
}
