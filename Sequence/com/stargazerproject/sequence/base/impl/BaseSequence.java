package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.sequence.SequenceTransaction;

public abstract class BaseSequence implements Sequence{
	
	protected Sequence sequence;

	@Override
	public void startSequence() {
		sequence.startSequence();
	}

	@Override
	public SequenceTransaction addModel(Optional<SequenceMethod> kernelModel) {
		return sequence.addModel(kernelModel);
	}

}
