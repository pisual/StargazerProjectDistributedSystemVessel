package com.stargazerproject.analysis.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.TransactionResultAnalysisExtend;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.ResultState;

public class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysisExtend{
	
	protected TransactionResultAnalysisExtend transactionResultAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<List<EventResult>> events) {
		return transactionResultAnalysisExtend.analysis(events);
	}

	@Override
	public Optional<ResultState> resultState() {
		return transactionResultAnalysisExtend.resultState();
	}

}
