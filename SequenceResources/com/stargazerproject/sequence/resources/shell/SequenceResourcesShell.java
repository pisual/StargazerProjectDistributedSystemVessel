package com.stargazerproject.sequence.resources.shell;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.sequence.Sequence;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Order>, BaseCharacteristic<Sequence<Order>>{
	
	@Autowired
	@Qualifier("eventBusImpl")
	private Bus<Event> bus;
	
	private Map<String, Order> orderTemporaryDepositMap = new HashMap<String, Order>();
	
	public SequenceResourcesShell() {}
	
	@Override
	@Bean(name="sequenceResourcesCharacteristic")
	@Lazy(true)
	public Optional<Sequence<Order>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void addSequence(Optional<Order> order) {
		orderTemporaryDepositMap.put(order.get().IDSequence().get(), order.get());
	}

	@Override
	public void startSequence(Optional<String> sequenceGroup) throws BusEventTimeoutException{
		Order order = orderTemporaryDepositMap.get(sequenceGroup.get());
		
		for(Event event : events){
			bus.push(Optional.of(event), Optional.of(TimeUnit.SECONDS), Optional.of(10));
		}
	}
	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		orderTemporaryDepositMap.remove(sequenceGroup.get());
	}
	
}