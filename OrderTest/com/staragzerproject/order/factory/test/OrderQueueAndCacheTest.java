package com.staragzerproject.order.factory.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.fasterxml.uuid.Generators;
import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class OrderQueueAndCacheTest {

	private static List<Order> orderList = new ArrayList<Order>(1000000);
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();
	
	private static LogMethod log;
	
	private static Cache<String, Order> cache;
	
	private static Segmentation<Optional<Event>> segmentation;
	
	private int TEST_NUMBER = 1000000;
	
	@Test
	public void SpringInit(){
		String args[] = {"debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}
	
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
		for (int i = 0; i < 2; i++) {
			orderList.add(getOrder());
		}
		log.INFO(this, "百万级Order序列创建完成");
	}
	
	@Test
	public void orderSegmentation() {
			for (int i = 0; i < 2; i++) {
				cache.put(orderList.get(i).IDSequence(), Optional.of(orderList.get(i)));
				orderList.get(i).segmentation(Optional.of(segmentation));
		}
		log.INFO(this, "百万级Order切分创建完成");
	}
	
	@Test
	public void waitTime(){
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
