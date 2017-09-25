package com.stargazerproject.queue.test;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class LogQueueTest {

	public static Queue<LogData> queue;
	
	public static QueueControl<LogData> queueControl;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none(); 
	
	public LogData getLogData(){
		return new LogData(Optional.of("LogTestTitle"), Optional.of("LogConsurm"), Optional.of(LogLevel.DEBUG));
	}
	
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

	@Test
	public void getqueueTest(){
		queue = BeanContainer.instance().getBean(Optional.of("logQueue"), Queue.class);
		queueControl = BeanContainer.instance().getBean(Optional.of("logQueue"), QueueControl.class);
	}
	
	
	@Test(timeout=10)
	public void queuePutTest(){
		queue.producer(Optional.of(getLogData()));
	}

	@Test(timeout=5000)
	public void cacheBitchPutTest(){
		for (int i = 0; i < 100000000; i++) {
			queue.producer(Optional.of(getLogData()));
		}
		System.out.println("Log Queue 亿级数据测试Put完毕");
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
		queue.producer(Optional.of(getLogData()));
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
