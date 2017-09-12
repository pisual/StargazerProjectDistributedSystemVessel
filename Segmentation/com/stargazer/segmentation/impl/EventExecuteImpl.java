package com.stargazer.segmentation.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;

@Component(value="eventExecute")
@Qualifier("eventExecute")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventExecuteImpl implements EventExecute{
	
	@Override
	public Boolean executeEvent(Optional<Cache<String, String>> parameter, Optional<Result> result) {
		System.out.println("Sleep");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wake Up");
		return Boolean.TRUE;
	}

}
