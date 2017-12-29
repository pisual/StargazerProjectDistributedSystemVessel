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
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusBlockMethod")
@Qualifier("eventBusBlockMethod")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusBlockMethodCharacteristic implements BusBlockMethod<Event>, BaseCharacteristic<BusBlockMethod<Event>>{

	@Autowired
	@Qualifier("logRecord")
	protected LogMethod l️og;
	
	private Queue<Event> event;
	
	public EventBusBlockMethodCharacteristic() {super();}

	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="eventBusBlockMethodCharacteristic")
	@Lazy(true)
	public Optional<BusBlockMethod<Event>> characteristic() {
		event = BeanContainer.instance().getBean(Optional.of("eventBusQueue"), Queue.class);
		return Optional.of(this);
	}

	@Override
	public Optional<Event> push(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		event.producer(busEvent);
		for(int i=0; i<timeout.get(); i++){
			sleep(timeUnit.get());
			if(busEvent.get().isComplete()){
				return busEvent;
			}
		}
		l️og.WARN(busEvent.get(), " Event Not completed at the specified time");
		throw new BusEventTimeoutException(event.toString()+"Event Not completed at the specified time");
	}
	
	private void sleep(TimeUnit timeUnit){
		try {
			switch (timeUnit) {
			case SECONDS:
				TimeUnit.SECONDS.sleep(1);
				break;
			case MICROSECONDS:
				TimeUnit.MICROSECONDS.sleep(1);
				break;
			case MILLISECONDS:
				TimeUnit.MILLISECONDS.sleep(1);
				break;
			case NANOSECONDS:
				TimeUnit.NANOSECONDS.sleep(1);
				break;
			default:
				l️og.WARN(timeUnit, "Other attributes are not supported, Will Use Default : SECONDS");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			l️og.ERROR(this, e.getMessage());
		}
	}
	
}
