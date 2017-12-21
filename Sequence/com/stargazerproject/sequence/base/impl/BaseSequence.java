package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.sequence.Sequence;

public class BaseSequence<K> implements Sequence<K>{
	
	protected Sequence<K> sequence;
	
	@Override
	public void addSequence(Optional<K> order) {
		sequence.addSequence(order);
	}

	@Override
	public void startSequence(Optional<String> sequenceGroup) throws BusEventTimeoutException {
		sequence.startSequence(sequenceGroup);
	}

	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		sequence.clear(sequenceGroup);
	}

}
