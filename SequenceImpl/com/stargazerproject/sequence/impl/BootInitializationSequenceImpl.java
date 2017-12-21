package com.stargazerproject.sequence.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.base.impl.BaseSequence;

@Component(value="bootInitializationSequence")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("bootInitializationSequence")
public class BootInitializationSequenceImpl extends BaseSequence<Order> implements StanderCharacteristicShell<Sequence<Order>>{

	@Override
	public void initialize(Optional<Sequence<Order>> sequenceArg) {
		sequence = sequenceArg.get();
	}

}
