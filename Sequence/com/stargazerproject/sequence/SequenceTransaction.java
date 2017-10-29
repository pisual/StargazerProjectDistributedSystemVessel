package com.stargazerproject.sequence;

import com.google.common.base.Optional;

public interface SequenceTransaction{
	public SequenceTransaction addModel(Optional<String> sequenceGroup, Optional<SequenceMethod> sequenceMethod);
	public void clear(Optional<String> sequenceGroup);
	public void startSequence(Optional<String> sequenceGroup);
}
