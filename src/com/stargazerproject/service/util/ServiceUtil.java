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
					System.out.println("Stargazer ServiceControlSystem Report : Wait "+workInServiceState+" Start");
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
		}
	}
}
