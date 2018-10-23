package com.stargazerproject.analysis.base.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.SequenceTransactionExecuteAnalysisExtend;
import com.stargazerproject.transaction.EventExecute;

public class SequenceTransactionExecuteAnalysis implements SequenceTransactionExecuteAnalysisExtend{
	
	private SequenceTransactionExecuteAnalysisExtend equenceTransactionExecuteAnalysisExtend;

	@Override
	public Optional<Boolean> analysis(Optional<List<EventExecute>> eventExecute) {
		return equenceTransactionExecuteAnalysisExtend.analysis(eventExecute);
	}

}
