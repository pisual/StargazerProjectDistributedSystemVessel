package com.stargazerproject.queue.test;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.cache.aop.configuration.OrderCacheAOPConfiguration;
import com.stargazerproject.cache.aop.configuration.SystemParameterAOPConfiguration;
import com.stargazerproject.cache.impl.OrderCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.OrderCacheCacheLoaderCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheLoadingCacheCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheRemovalListenerCharacteristic;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.OrderCahceShell;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.OrderCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterBuiltInCacheServer;
import com.stargazerproject.cache.server.listener.impl.OrderCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.OrderCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.model.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
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
public class EventQueueTest {

	public static Queue<Event> queue;
	
	public static QueueControl<Event> queueControl;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  

	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
				/**Itself Configuration Class**/
				EventQueue.class,
				EventDisruptorShell.class,
				EventFactory.class,
				EventHandler.class,
				EventQueueThreadFactory.class,
				EventQueueServer.class,
				EventQueueServerListener.class,
				EventQueueServerManage.class,

		     /******Depend Configuration Class******/
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
				
				/**Depend SystemParameterCahce**/
				SystemParameterCahce.class,
				SystemParameterCahceCharacteristic.class,
				SystemParameterCahceShell.class,
				SystemParameterBuiltInCacheServer.class,
				SystemParameterCacheServerListener.class,
				SystemParameterCacheServerManage.class,
				
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
	
	public Event getEventData(){
		return new Event(Optional.of("ID"), Optional.of(new SystemParameterCahce()));
	}
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

	@Test
	public void getqueueTest(){
		queue = BeanContainer.instance().getBean(Optional.of("eventQueue"), Queue.class);
		queueControl = BeanContainer.instance().getBean(Optional.of("eventQueue"), QueueControl.class);
	}
	
	
	@Test(timeout=10)
	public void queuePutTest(){
		queue.producer(Optional.of(getEventData()));
	}

	@Test(timeout=20000)
	public void cacheBitchPutTest(){
		for (int i = 0; i < 100000000; i++) {
			queue.producer(Optional.of(getEventData()));
		}
		System.out.println("Event Queue 亿级(100000000个)数据测试Put完毕");
	}
	
	@Test(timeout=10)
	public void queueStartAgainTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Disruptor.start() must only be called once.");  
		queueControl.start();
	}
	
	@Test(timeout=10)
	public void queuehutdownTest(){  
		queueControl.shutdown();
	}
	
	@Test(timeout=10)
	public void queuehutdownAgainTest(){  
		queueControl.shutdown();
	}
	
	@Test(timeout=10)
	public void queueStartAgainTest2(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Disruptor.start() must only be called once.");  
		queueControl.start();
	}
	
	@Test
	public void serviceStopTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.stopAllService();
	}
	
	@Test
	public void serviceStopLaterGetTest(){
		queue.producer(Optional.of(getEventData()));
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
