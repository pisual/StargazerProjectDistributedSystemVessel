package com.stargazerproject.cell.impl.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.google.common.base.Optional;
import com.netflix.hystrix.HystrixCommand;
import com.stargazerproject.cell.impl.QuickPassCell;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

public class QuickPassCellTest {
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(QuickPassCell.class);
	}
	
	@Test
	public void QuickPassCellRunTest(){
		
		for(int i=0;i<1000000;i++){
		HystrixCommand hystrixCommand = BeanContainer.instance().getBean(Optional.of("quickPassCell"), HystrixCommand.class);
		try {
			hystrixCommand.queue().get(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		}
		System.out.println("Over");
	}
}
