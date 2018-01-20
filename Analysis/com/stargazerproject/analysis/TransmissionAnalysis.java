package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.TransmissionContent;

public interface TransmissionAnalysis {
	public Optional<Boolean> analysis(Optional<TransmissionContent> transmissionContent);
}
