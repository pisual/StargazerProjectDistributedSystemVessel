package com.stargazerproject.cache.impl.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.aop.configuration.CacheAOPConfiguration;
import com.stargazerproject.cache.aop.configuration.SystemParameterAOPConfiguration;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.SystemParameterBuiltInCacheServer;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

/** 
 *  @name SystemParameterCahce 测试
 *  @illustrate SystemParameterCahce 重构后测试
 *  @author Felixerio
 *  @Version 4.0.0
 *  **/
public class SystemParameterCahceBeanContainerTest {
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
		/**Itself Configuration Class**/
		SystemParameterCahce.class,
		SystemParameterCahceCharacteristic.class,
		SystemParameterCahceShell.class,
		SystemParameterBuiltInCacheServer.class,
		SystemParameterCacheServerListener.class,
		SystemParameterCacheServerManage.class,
		CacheAOPConfiguration.class,
		
		/**Depend Configuration Class**/
		StargazerProjectParameterList.class,
		GroupLogConfiguration.class
		
		);
	}
	
	@Test
	public void getSystemParameterCahceBeanContainer(){
		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
	}
	
	@Test
	public void putTest(){
		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		cache.put(Optional.of("TestKey"), Optional.of("TestValue"));
	}
	
//	@Test
//	public void getTest(){
//		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
//		System.out.println(cache.get(Optional.of("TestKey")).get());
//	}
	public static void main(String[] args) {
		Cache<String, String> cache = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		cache.put(Optional.of("TestKey"), Optional.of("TestValue"));
	}
}
