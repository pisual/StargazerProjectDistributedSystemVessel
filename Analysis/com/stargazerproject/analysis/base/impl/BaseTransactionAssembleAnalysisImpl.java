package com.stargazerproject.analysis.base.impl;

import java.util.Collection;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.transaction.EventAssemble;

public abstract class BaseTransactionAssembleAnalysisImpl implements TransactionAssembleAnalysis {
	
	protected TransactionAssembleAnalysis transactionAssembleAnalysis;

	@Override
	public Optional<Boolean> analysis(Optional<Collection<EventAssemble>> eventsList) {
		return transactionAssembleAnalysis.analysis(eventsList);
	}

	@Override
	public void addEvent(Optional<EventAssemble> eventAssemble) {
		transactionAssembleAnalysis.addEvent(eventAssemble);
	}

}
