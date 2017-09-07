package com.staragzerproject.order.factory.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.fasterxml.uuid.Generators;
import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazer.segmentation.Segmentation;
import com.stargazer.segmentation.impl.EventExecute;
import com.stargazer.segmentation.impl.EventSegmentation;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.aop.configuration.OrderCacheAOPConfiguration;
import com.stargazerproject.cache.aop.configuration.SystemParameterAOPConfiguration;
import com.stargazerproject.cache.impl.BigCacheIndexCahce;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.OrderCache;
import com.stargazerproject.cache.impl.OrderParameterCache;
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
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.order.impl.Order;
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
public class OrderQueueAndCacheTest {

	private static List<Order> orderList = new ArrayList<Order>(1000000);
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();
	
	private static LogMethod log;
	
	private static Cache<String, Order> cache;
	
	private static Segmentation<Optional<Event>> segmentation;
	
	private int TEST_NUMBER = 1000000;
	
	/**初始化区域开始**/
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
				
				/**Itself Configuration Class**/
				SystemParameterCahce.class,
				SystemParameterCahceCharacteristic.class,
				SystemParameterCahceShell.class,
				SystemParameterBuiltInCacheServer.class,
				SystemParameterCacheServerListener.class,
				SystemParameterCacheServerManage.class,

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
				
				/**Depend EventQueue**/
				EventQueue.class,
				EventDisruptorShell.class,
				EventFactory.class,
				EventHandler.class,
				EventQueueThreadFactory.class,
				EventQueueServer.class,
				EventQueueServerListener.class,
				EventQueueServerManage.class,
				EventConsumer.class,
				
				/**Depend LogCache**/
				LogQueue.class,
				LogDisruptorShell.class,
				LogEventFactory.class,
				LogHandler.class,
				LogQueueThreadFactory.class,
				LogQueueServer.class,
				LogQueueServerListener.class,
				LogQueueServerManage.class,
				
				/**Depend OrderCache**/
				OrderCache.class,
				OrderCacheCacheLoaderCharacteristic.class,
				OrderCacheLoadingCacheCharacteristic.class,
				OrderCacheRemovalListenerCharacteristic.class,
				OrderCahceShell.class,
				OrderCacheServer.class,
				OrderCacheServerListener.class,
				OrderCacheServerManage.class,
				
				/**Depend AOP**/
				OrderCacheAOPConfiguration.class,
				SystemParameterAOPConfiguration.class,
				
				/**Depend Resources**/
				StargazerProjectParameterList.class,
				ServiceParameterList.class,
				
				/**Depend Log**/
				GroupLogConfiguration.class,
				
				/**Depend Service**/
				GroupServiceConfiguration.class,
				
				EventSegmentation.class,
				EventExecute.class
				);
	}
	/**初始化区域结束**/
	
	/**内部函数区域开始**/
	
	private Order getOrder() {
		OrderFluentFactory o = new OrderFluentFactory();
		Order order = o.addID(Optional.of(getUUID())).addTransmission()
				       .addReceiveTarget(Optional.of("192.168.1.1"), Optional.of(1010))
				       .addSourceTarget(Optional.of("192.168.1.2"), Optional.of(2020)).next().addEvents()
					   .addCache(Optional.of(new OrderParameterCache()))
				       .addparameter(Optional.of("Test"), Optional.of(getUUID())).addnextEvent().addEvents()
					   .addCache(Optional.of(new OrderParameterCache()))
				       .addparameter(Optional.of("Test"), Optional.of(getUUID())).complete();
		return order;
	}
	
	private String getUUID(){
		UUID uuid = Generators.randomBasedGenerator().generate();
		return uuid.toString();
	}
	
	/**内部函数区域结束**/
	
	
	/**测试函数序列区域开始**/
	

	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
	
	@Test
	public void objectInit(){
		log = BeanContainer.instance().getBean(Optional.of("logRecord"), LogMethod.class);
		cache = BeanContainer.instance().getBean(Optional.of("orderCache"), Cache.class);
		segmentation = BeanContainer.instance().getBean(Optional.of("eventSegmentation"), Segmentation.class);
	}
	
	@Test
	public void getOrderList() {
		for (int i = 0; i < 1000000; i++) {
			orderList.add(getOrder());
		}
		log.INFO(this, "百万级Order序列创建完成");
	}
	
	@Test
	public void orderSegmentation() {
		for (int j = 0; j < 100000; j++) {
			for (int i = 0; i < 1000000; i++) {
				orderList.get(i).segmentation(segmentation);;
			}
		}
		log.INFO(this, "百万级Order切分创建完成");
	}
	
	public static void main(String[] args) {
		OrderQueueAndCacheTest orderQueueAndCacheTest = new OrderQueueAndCacheTest();
		orderQueueAndCacheTest.serviceStartTest();
		orderQueueAndCacheTest.objectInit();
		orderQueueAndCacheTest.getOrderList();
		orderQueueAndCacheTest.orderSegmentation();
	}
	
}
