package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;


public interface LogAnalysis {
	public Optional<Boolean> analysis(Optional<LogData> logData);
}
