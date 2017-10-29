package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.sequence.SequenceTransaction;

public abstract class BaseSequence implements Sequence{
	
	protected Sequence sequence;

	@Override
	public void startSequence(Optional<String> sequenceGroup) {
		sequence.startSequence(sequenceGroup);
	}

	@Override
	public SequenceTransaction addModel(Optional<String> sequenceGroup, Optional<SequenceMethod> sequenceMethod) {
		return sequence.addModel(sequenceGroup, sequenceMethod);
	}
	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		sequence.clear(sequenceGroup);
	}

}
