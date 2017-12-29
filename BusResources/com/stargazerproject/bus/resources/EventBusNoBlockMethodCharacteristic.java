package com.stargazerproject.bus.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusEventListen;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.impl.EventBusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusNoBlockMethod")
@Qualifier("eventBusNoBlockMethod")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusNoBlockMethodCharacteristic implements BusNoBlockMethod<Event>, BaseCharacteristic<BusNoBlockMethod<Event>>{

	@Autowired
	@Qualifier("logRecord")
	protected LogMethod l️og;
	
	private Queue<Event> event;
	
	public EventBusNoBlockMethodCharacteristic() {super();}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="eventBusNoBlockMethodCharacteristic")
	@Lazy(true)
	public Optional<BusNoBlockMethod<Event>> characteristic() {
		event = BeanContainer.instance().getBean(Optional.of("eventBusQueue"), Queue.class);
		return Optional.of(this);
	}

	@Override
	public Optional<BusObserver<Event>> pushNoBlock(Optional<Event> busEvent, Optional<BusEventListen> BusEventListen, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		BusObserver<Event> eventBusObserver = new EventBusObserver(busEvent);
		event.producer(busEvent);
		return Optional.of(eventBusObserver);
	}
	
}
