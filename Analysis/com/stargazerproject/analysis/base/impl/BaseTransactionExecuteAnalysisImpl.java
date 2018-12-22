package com.stargazerproject.analysis.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.transaction.Event;

public abstract class BaseTransactionExecuteAnalysisImpl implements TransactionExecuteAnalysis {
	
	protected TransactionExecuteAnalysis transactionExecuteAnalysis;

	@Override
	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute) {
		return transactionExecuteAnalysis.analysis(eventExecute);
	}

}
