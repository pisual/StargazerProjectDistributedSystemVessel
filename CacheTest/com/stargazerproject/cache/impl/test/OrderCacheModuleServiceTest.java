package com.stargazerproject.cache.impl.test;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.aop.configuration.OrderCacheAOPConfiguration;
import com.stargazerproject.cache.aop.configuration.SystemParameterAOPConfiguration;
import com.stargazerproject.cache.impl.BigCacheIndexCahce;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.OrderCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.BigCacheIndexCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheManagerCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheCacheLoaderCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheLoadingCacheCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheRemovalListenerCharacteristic;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.BigCacheIndexCahceShell;
import com.stargazerproject.cache.impl.resources.shell.ByteArrayCacheShell;
import com.stargazerproject.cache.impl.resources.shell.OrderCahceShell;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.BigCacheIndexCacheBuiltInCacheServer;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.OrderCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterBuiltInCacheServer;
import com.stargazerproject.cache.server.listener.impl.BigCacheIndexCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.OrderCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.BigCacheIndexCacheServerManage;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.OrderCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.order.impl.AddressTarget;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.order.impl.Transaction;
import com.stargazerproject.order.impl.Transmission;
import com.stargazerproject.queue.impl.EventQueue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.queue.impl.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.impl.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.impl.EventFactory;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.queue.resources.impl.EventQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.LogEventFactory;
import com.stargazerproject.queue.resources.impl.LogHandler;
import com.stargazerproject.queue.resources.impl.LogQueueThreadFactory;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.resources.service.ServiceParameterList;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

@FixMethodOrder(MethodSorters.JVM)
public class OrderCacheModuleServiceTest{
	
	public static Cache<String, Order> cache;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  

	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
				/**Itself Configuration Class**/
				OrderCache.class,
				OrderCacheCacheLoaderCharacteristic.class,
				OrderCacheLoadingCacheCharacteristic.class,
				OrderCacheRemovalListenerCharacteristic.class,
				OrderCahceShell.class,
				OrderCacheServer.class,
				OrderCacheServerListener.class,
				OrderCacheServerManage.class,

		     /******Depend Configuration Class******/
				/**Depend BigCacheIndexCahce**/
				BigCacheIndexCahce.class,
				BigCacheIndexCahceCharacteristic.class,
				BigCacheIndexCahceShell.class,
				BigCacheIndexCacheBuiltInCacheServer.class,
				BigCacheIndexCacheServerListener.class,
				BigCacheIndexCacheServerManage.class,
				
				/**Depend ByteArrayCache**/
				ByteArrayCache.class,
				ByteArrayCacheCacheConfigurationCharacteristic.class,
				ByteArrayCacheCacheManagerCharacteristic.class,
				ByteArrayCacheConfigurationCharacteristic.class,
				ByteArrayCacheShell.class,
				ByteArrayCacheServer.class,
				ByteArrayCacheServerListener.class,
				ByteArrayCacheServerManage.class,
				
				/**Depend OrderCache**/
				SystemParameterCahce.class,
				SystemParameterCahceCharacteristic.class,
				SystemParameterCahceShell.class,
				SystemParameterBuiltInCacheServer.class,
				SystemParameterCacheServerListener.class,
				SystemParameterCacheServerManage.class,
				
				/**Depend EventQueue**/
				EventQueue.class,
				EventDisruptorShell.class,
				EventFactory.class,
				EventHandler.class,
				EventQueueThreadFactory.class,
				EventQueueServer.class,
				EventQueueServerListener.class,
				EventQueueServerManage.class,
				
				/**Depend LogCache**/
				LogQueue.class,
				LogDisruptorShell.class,
				LogEventFactory.class,
				LogHandler.class,
				LogQueueThreadFactory.class,
				LogQueueServer.class,
				LogQueueServerListener.class,
				LogQueueServerManage.class,
				
				/**Depend AOP**/
				OrderCacheAOPConfiguration.class,
				SystemParameterAOPConfiguration.class,
				
				/**Depend Resources**/
				StargazerProjectParameterList.class,
				ServiceParameterList.class,
				
				/**Depend Log**/
				GroupLogConfiguration.class,
				
				/**Depend Service**/
				GroupServiceConfiguration.class
				);
	}
	
	public Order getTestModelOrder(){
		Transmission transmission = new Transmission(Optional.of(new AddressTarget(Optional.fromNullable("10.0.1.1"), Optional.fromNullable(1234))), Optional.of(new AddressTarget(Optional.fromNullable("10.0.1.1"), Optional.fromNullable(1234))));
		Event event = new Event(Optional.of("ID"), Optional.of(new SystemParameterCahce()));
		Transaction transaction = new Transaction(Optional.of("T ID"), Optional.of(event));
		Order order = new Order(Optional.of("Order ID"), Optional.of(transmission), Optional.of(transaction));
		return(order);
	}
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

	@Test
	public void getCacheTest(){
		cache = BeanContainer.instance().getBean(Optional.of("orderCache"), Cache.class);
	}
	
	@Test(timeout=10)
	public void cachePutTest(){
		cache.put(Optional.of("TestKey"), Optional.of(getTestModelOrder()));
	}
	
	@Test
	public void cacheGetTest(){
		System.out.println(cache.get(Optional.of("TestKey")).get().toString());
	}
	
	@Test
	public void cacheRemoveTest(){
		cache.remove(Optional.of("TestKey"));
	}
	
	@Test
	public void cacheRemoveLatersTest(){
		expectedException.equals(NullPointerException.class);
		expectedException.expectMessage("Stargazer ServiceControlServer Report :  Key : TestKey Value is Null");  
		System.out.println(cache.get(Optional.of("TestKey")).get());
	}
	
	@Test(timeout=10000)
	public void cacheBitchPutTest(){
		for (int i = 0; i < 1000000; i++) {
			cache.put(Optional.of("TestKey" + i), Optional.of(getTestModelOrder()));
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
		cache.put(Optional.of("TestKey"), Optional.of(getTestModelOrder()));
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
