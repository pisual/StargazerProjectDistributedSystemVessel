package com.stargazerproject.microkernel.base.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.microkernel.KernelSequenceModel;

public abstract class BaseKernelModel implements KernelSequenceModel{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	protected Boolean waitResult(Optional<String> waitPoint, Optional<Integer> retriesNumberArg, Optional<Integer> intervaltimeArg){
		int retriesNumber = retriesNumberArg.get();
		while(true){
			if(systemParameter.get(waitPoint).get().equals("Continue")){
				return Boolean.TRUE;
			}
			else{
				wait(intervaltimeArg.get());
				if( (--retriesNumber) < 0){
					return Boolean.FALSE;
				}
			}
		}
	}
	
	private void wait(int time){
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected void initialization(Optional<String> waitPoint){
		systemParameter.put(waitPoint, Optional.of("Wait"));
	}

}
