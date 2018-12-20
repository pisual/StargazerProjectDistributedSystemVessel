package com.stargazerproject.analysis.extend;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;

public interface EventAssembleAnalysisExtend extends EventAssembleAnalysis{
	
	public void injectEventParameter(Optional<String> Key, Optional<String> value);
	
}
