package com.stargazerproject.bus.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.Event;

@Component(value="eventBusNoBlockMethodCharacteristic")
@Qualifier("eventBusNoBlockMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusNoBlockMethodCharacteristic implements BusNoBlockMethod<Event>, BaseCharacteristic<BusNoBlockMethod<Event>>{

	@Autowired
	@Qualifier("logRecord")
	protected LogMethod l️og;
	
	@Autowired
	@Qualifier("eventBusQueue")
	private Queue<Event> event;
	
	public EventBusNoBlockMethodCharacteristic() {
		super();
		}
	
	@Override
	public Optional<BusNoBlockMethod<Event>> characteristic() {
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Event>> pushNoBlock(Optional<Event> busEvent) {
		BusObserver<Event> eventBusObserver = BeanContainer.instance().getBean(Optional.of("eventBusObserver"), BusObserver.class);
		eventBusObserver.injectEvent(busEvent);
		event.producer(busEvent);
		return Optional.of(eventBusObserver);
	}
	
}
