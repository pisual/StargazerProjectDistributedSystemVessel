package com.stargazerproject.service.util;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.spring.container.impl.BeanContainer;

public class ServiceUtil {
	
	public static void dependOnDelay(String ... workInServiceStates){
		for(String workInServiceState: workInServiceStates){
			WorkInServiceState serverState = BeanContainer.instance().getBean(Optional.of(workInServiceState), WorkInServiceState.class);
			while(serverState.getServerstate() == Boolean.FALSE){
				try {
					System.out.println("等待"+workInServiceState);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
		}
	}
}