package com.stargazerproject.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class OrderMessageQueueTest {

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
	
	public static void main(String[] args) {
		String argss[] = {"–debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(argss);
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
//		MessageQueue<Order> orderMessageQueue = BeanContainer.instance().getBean(Optional.of("orderMessageQueueShall"), MessageQueue.class);
//		List list = new ArrayList();
//		orderMessageQueue.put(Optional.of(list));
	}
}
