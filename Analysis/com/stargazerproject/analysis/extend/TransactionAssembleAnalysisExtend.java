package com.stargazerproject.analysis.extend;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.transaction.EventAssemble;

public interface TransactionAssembleAnalysisExtend extends TransactionAssembleAnalysis{

	public void addEvent(Optional<EventAssemble> eventAssemble);
	
}
