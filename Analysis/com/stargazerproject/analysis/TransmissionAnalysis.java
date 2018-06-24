package com.stargazerproject.analysis;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResult;

public interface TransmissionAnalysis {

	/** @illustrate 结果分析器 **/
	public Optional<Boolean> analysis(Optional<List<EventResult>> events);
	
}
