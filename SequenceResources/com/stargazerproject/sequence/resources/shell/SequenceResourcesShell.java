package com.stargazerproject.sequence.resources.shell;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.transaction.base.impl.BaseEvent;
import com.stargazerproject.transaction.impl.Order;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Order>, BaseCharacteristic<Sequence<Order>>{
	
	@Autowired
	@Qualifier("eventBusImpl")
	private Bus<BaseEvent> bus;
	
}