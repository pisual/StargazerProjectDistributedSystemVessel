package com.stargazer.segmentation.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.order.base.impl.BaseEvent;

@Component(value="eventBusBlockSegmentation")
@Qualifier("eventBusBlockSegmentation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusBlockSegmentation implements Segmentation<Optional<BaseEvent>>{
	
	@Autowired
	@Qualifier("eventBusBlockMethod")
	private BusBlockMethod<BaseEvent> eventBusBlockMethod;
	
	@Override
	public void batchSegmentation(Optional<BaseEvent> event) {
		try {
			eventBusBlockMethod.push(event, Optional.of(TimeUnit.SECONDS), Optional.of(10));
		} catch (BusEventTimeoutException e) {
			event.get().skipEvent();
		}
	}
}
