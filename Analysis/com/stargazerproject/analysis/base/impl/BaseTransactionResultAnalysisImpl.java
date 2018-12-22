package com.stargazerproject.analysis.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.ResultState;

public abstract class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<Boolean> analysis(Optional<List<EventResult>> events) {
		return transactionResultAnalysis.analysis(events);
	}

	@Override
	public Optional<ResultState> resultState() {
		return transactionResultAnalysis.resultState();
	}

}
