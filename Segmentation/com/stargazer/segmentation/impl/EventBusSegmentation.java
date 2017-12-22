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
import com.stargazerproject.order.impl.Event;

@Component(value="eventSegmentation")
@Qualifier("eventSegmentation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusSegmentation implements Segmentation<Optional<Event>>{
	
	@Autowired
	@Qualifier("eventBusBlockMethod")
	private BusBlockMethod<Event> eventBusBlockMethod;
	
	@Override
	public void batchSegmentation(Optional<Event> event) {
		try {
			eventBusBlockMethod.push(event, Optional.of(TimeUnit.SECONDS), Optional.of(10));
		} catch (BusEventTimeoutException e) {
			event.get().skipEvent();
		}
	}
}
