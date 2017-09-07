package com.stargazer.segmentation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Execute;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Result;

@Component(value="eventExecute")
@Qualifier("eventExecute")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventExecute implements Execute{
	
	private float num = 0F;

	@Override
	public Boolean executeEvent(Optional<Cache<String, String>> parameter, Optional<Result> result) {
		num++;
		if(num>900000000){
			System.out.println(num);
		}
		return Boolean.TRUE;
	}

}
