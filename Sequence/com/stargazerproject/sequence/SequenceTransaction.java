package com.stargazerproject.sequence;

import com.google.common.base.Optional;

public interface SequenceTransaction<K>{
	public SequenceTransaction<K> addModel(Optional<String> sequenceGroup, Optional<K> event);
	public void clear(Optional<String> sequenceGroup);
	public void startSequence(Optional<String> sequenceGroup);
}
