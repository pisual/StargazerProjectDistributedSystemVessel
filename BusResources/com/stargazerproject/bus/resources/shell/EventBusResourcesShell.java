package com.stargazerproject.bus.resources.shell;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusEventListen;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusResourcesShell")
@Qualifier("eventBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusResourcesShell implements Bus<Event>, BaseCharacteristic<Bus<Event>>{
	
	private Optional<BusBlockMethod<Event>> busBlockMethod;
	private Optional<BusNoBlockMethod<Event>> busNoBlockMethod;
	
	public EventBusResourcesShell() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="eventBusResourcesCharacteristic")
	@Lazy(true)
	public Optional<Bus<Event>> characteristic() {
		busBlockMethod = BeanContainer.instance().getBean(Optional.of("eventBusBlockMethodCharacteristic"), Optional.class);
		busNoBlockMethod = BeanContainer.instance().getBean(Optional.of("eventBusNoBlockMethodCharacteristic"), Optional.class);
		return Optional.of(this);
	}

	@Override
	public Optional<Event> push(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		return busBlockMethod.get().push(busEvent, timeUnit, timeout);
	}
	
	@Override
	public Optional<BusObserver<Event>> pushNoBlock(Optional<Event> busEvent, Optional<BusEventListen> BusEventListen, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		return busNoBlockMethod.get().pushNoBlock(busEvent, BusEventListen, timeUnit, timeout);
	}

	@Override
	public void startBus() {
		
	}

	@Override
	public void stopBus() {
		
	}
	
}