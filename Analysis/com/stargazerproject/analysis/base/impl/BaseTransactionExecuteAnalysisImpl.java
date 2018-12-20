package com.stargazerproject.analysis.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.TransactionExecuteAnalysisExtend;
import com.stargazerproject.transaction.Event;

public class BaseTransactionExecuteAnalysisImpl implements TransactionExecuteAnalysisExtend{
	
	protected TransactionExecuteAnalysisExtend transactionExecuteAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute) {
		return transactionExecuteAnalysisExtend.analysis(eventExecute);
	}

}
