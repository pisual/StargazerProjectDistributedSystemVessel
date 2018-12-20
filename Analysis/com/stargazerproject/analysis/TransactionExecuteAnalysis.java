package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.transaction.Event;

public interface TransactionExecuteAnalysis {
	
	/** @illustrate 执行分析器 **/
	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute);

}
