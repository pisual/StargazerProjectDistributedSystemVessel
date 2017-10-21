package com.stargazerproject.sequence;

import com.google.common.base.Optional;

public interface SequenceTransaction{
	public SequenceTransaction addModel(Optional<SequenceMethod> sequenceMethod);
	public void startSequence();
}
