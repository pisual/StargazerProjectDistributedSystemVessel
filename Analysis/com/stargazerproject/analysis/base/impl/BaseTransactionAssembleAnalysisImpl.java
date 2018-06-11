package com.stargazerproject.analysis.base.impl;

import java.util.Collection;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.TransactionAssembleAnalysisExtend;
import com.stargazerproject.transaction.EventAssemble;

public class BaseTransactionAssembleAnalysisImpl implements TransactionAssembleAnalysisExtend{
	
	protected TransactionAssembleAnalysisExtend transactionAssembleAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<Collection<EventAssemble>> eventsList) {
		return transactionAssembleAnalysisExtend.analysis(eventsList);
	}

	@Override
	public void addEvent(Optional<EventAssemble> eventAssemble) {
		transactionAssembleAnalysisExtend.addEvent(eventAssemble);
	}

}
