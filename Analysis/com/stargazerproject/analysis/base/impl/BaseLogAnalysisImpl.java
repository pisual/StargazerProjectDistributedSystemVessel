package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.LogAnalysis;
import com.stargazerproject.log.model.LogData;

public class BaseLogAnalysisImpl implements LogAnalysis{

	protected LogAnalysis logAnalysis;
	
	@Override
	public Optional<Boolean> analysis(Optional<LogData> logData) {
		return logAnalysis.analysis(logData);
	}

}
