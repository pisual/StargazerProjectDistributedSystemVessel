package com.stargazerproject.sequence.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.SequenceObserver;

public class SequenceObserverImpl<K, RA> implements SequenceObserver<K>{
	
	private RA resultAnalysis;
	
	public SequenceObserverImpl(Optional<RA> resultAnalysisArg, Optional<List<K>> transactionList) {
		resultAnalysis = resultAnalysisArg.get();
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional failResultList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional sequenceResultList() {
		// TODO Auto-generated method stub
		return null;
	}

}
