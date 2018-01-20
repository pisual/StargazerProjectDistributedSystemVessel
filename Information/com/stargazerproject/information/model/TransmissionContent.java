package com.stargazerproject.information.model;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransmissionAnalysis;

public class TransmissionContent {
	
	private Object context;
	
	private String contextType;
	
	private TransmissionContent(Optional<Object> contextArg, Optional<String> contextTypeArg){
		context = contextArg.get();
		contextType = contextTypeArg.get();
	}
	
	public void analysis(Optional<TransmissionAnalysis> transmissionAnalysis){
		transmissionAnalysis.get().analysis(Optional.of(this));
	}

}
