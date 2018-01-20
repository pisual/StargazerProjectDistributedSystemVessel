package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransmissionAnalysis;
import com.stargazerproject.information.model.TransmissionContent;

public class BaseTransmissionAnalysisImpl implements TransmissionAnalysis{
	
	protected TransmissionAnalysis transmissionAnalysis;

	@Override
	public Optional<Boolean> analysis(Optional<TransmissionContent> transmissionContent) {
		return transmissionAnalysis.analysis(transmissionContent);
	}

}
