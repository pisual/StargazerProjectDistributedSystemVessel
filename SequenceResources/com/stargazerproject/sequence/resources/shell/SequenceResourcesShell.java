package com.stargazerproject.sequence.resources.shell;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Event>, BaseCharacteristic<Sequence<Event>>{
	
	private Bus<Event> bus;
	
	private Multimap<String, Event> scoreMultimap = LinkedHashMultimap.create(); 
	
	public SequenceResourcesShell() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="sequenceResourcesCharacteristic")
	@Lazy(true)
	public Optional<Sequence<Event>> characteristic() {
		bus = BeanContainer.instance().getBean(Optional.of("eventBusImpl"), Bus.class);
		return Optional.of(this);
	}

	@Override
	public SequenceTransaction<Event> addModel(Optional<String> sequenceGroup, Optional<Event> event) {
		scoreMultimap.put(sequenceGroup.get(), event.get());
		return this;
	}

	@Override
	public void startSequence(Optional<String> sequenceGroup) throws BusEventTimeoutException{
		Collection<Event> events = scoreMultimap.get(sequenceGroup.get());
		for(Event event : events){
			bus.push(Optional.of(event), Optional.of(TimeUnit.SECONDS), Optional.of(10));
		}
	}
	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		scoreMultimap.removeAll(sequenceGroup.get());
	}
	
}