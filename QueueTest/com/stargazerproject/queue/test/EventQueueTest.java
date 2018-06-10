package com.stargazerproject.queue.test;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.fasterxml.uuid.Generators;
import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;
import com.stargazerproject.transaction.base.impl.BaseEvent;
import com.stargazerproject.transaction.factory.OrderFluentFactory;
import com.stargazerproject.transaction.impl.Order;

@FixMethodOrder(MethodSorters.JVM)
public class EventQueueTest {

	public static Queue<BaseEvent> queue;
	
	private static Cache<String, Order> cache;
	
	public static QueueControl<BaseEvent> queueControl;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  
	
	@Test
	public void SpringInit(){
		String args[] = {"debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}
	
	public BaseEvent getEventData(){
		return new BaseEvent(Optional.of("ID"), Optional.of(new SystemParameterCahce()));
	}
	
	/**内部函数区域开始**/
	
	private Order getOrder(String uuid) {
		OrderFluentFactory o = new OrderFluentFactory();
		Order order = o.addID(Optional.of(uuid)).addTransmission()
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
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

	@Test
	public void getqueueTest(){
		queue = BeanContainer.instance().getBean(Optional.of("eventQueue"), Queue.class);
		cache = BeanContainer.instance().getBean(Optional.of("orderCache"), Cache.class);
		queueControl = BeanContainer.instance().getBean(Optional.of("eventQueue"), QueueControl.class);
	}
	
	
	@Test(timeout=10)
	public void queuePutTest(){
		queue.producer(Optional.of(getEventData()));
	}

	@Test(timeout=20000)
	public void cacheBitchPutTest(){
		for (int i = 0; i < 1; i++) {
			BaseEvent event = getEventData();
			cache.put(event.IDSequence(), Optional.of(getOrder(event.IDSequence().get())));
			queue.producer(Optional.of(event));
		}
		System.out.println("BaseEvent Queue 亿级(100000000个)数据测试Put完毕");
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
