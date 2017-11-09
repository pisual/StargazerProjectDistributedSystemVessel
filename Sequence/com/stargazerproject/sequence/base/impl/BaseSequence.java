package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceTransaction;

public abstract class BaseSequence<K> implements Sequence<K>{
	
	protected Sequence<K> sequence;

	@Override
	public void startSequence(Optional<String> sequenceGroup) {
		sequence.startSequence(sequenceGroup);
	}

	@Override
	public SequenceTransaction<K> addModel(Optional<String> sequenceGroup, Optional<K> event) {
		return sequence.addModel(sequenceGroup, event);
	}
	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		sequence.clear(sequenceGroup);
	}

}
