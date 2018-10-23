package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventExecute;

public interface TransactionExecuteAnalysis {
	
	/** @illustrate 执行分析器 **/
	public Optional<Boolean> analysis(Optional<List<EventExecute> > eventExecute);

}
