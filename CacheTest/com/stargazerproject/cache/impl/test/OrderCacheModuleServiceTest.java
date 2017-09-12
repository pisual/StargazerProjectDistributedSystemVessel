package com.stargazerproject.cache.impl.test;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.fasterxml.uuid.Generators;
import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class OrderCacheModuleServiceTest{
	
	public static Cache<String, Order> cache;
	
	private static Order order;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  
	
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
	
	@Test
	public void SpringInit(){
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize();
	}
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
	
	@Test
	public void initOrder(){
		order = getOrder();
	}

	@Test
	public void getCacheTest(){
		cache = BeanContainer.instance().getBean(Optional.of("orderCache"), Cache.class);
	}
	
	@Test(timeout=20)
	public void cachePutTest(){
		cache.put(Optional.of("TestKey"), Optional.of(order));
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
	
	@Test(timeout=20000)
	public void cacheBitchPutTest(){
		for (int i = 0; i < 1000000; i++) {
			cache.put(Optional.of("TestKey" + i), Optional.of(getOrder()));
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
		cache.put(Optional.of("TestKey"), Optional.of(getOrder()));
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
