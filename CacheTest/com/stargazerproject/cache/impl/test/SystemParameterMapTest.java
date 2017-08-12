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

import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupLogConfiguration.class, SystemParameterCahceShell.class,StargazerProjectParameterList.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SystemParameterMapTest implements WorkInTest{
	
	@Autowired
	@Qualifier("systemParameterMap")
	private BaseCharacteristic<Map<String, String>> systemParameterMap;

	@Autowired
	@Qualifier("stargazerProjectParameterList")
	private StargazerProjectParameterList stargazerProjectParameterList;
	
	static{
		System.setProperty("spring.profiles.active","develop");
	}
	
	@Test
	@Override
	public void interfaceInitialize() {
		System.out.println(systemParameterMap);
		System.out.println(stargazerProjectParameterList);
	}
	
}
