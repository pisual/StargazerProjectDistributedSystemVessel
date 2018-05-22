package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceResult;

public class BaseSequence<K> implements Sequence<K>{
	
	protected Sequence<K> sequence;

}
