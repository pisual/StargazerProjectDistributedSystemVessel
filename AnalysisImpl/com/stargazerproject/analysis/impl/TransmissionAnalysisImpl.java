package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransmissionAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransmissionAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="transmissionAnalysisImpl")
@Qualifier("transmissionAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransmissionAnalysisImpl extends BaseTransmissionAnalysisImpl implements StanderCharacteristicShell<TransmissionAnalysis>{
	
	public TransmissionAnalysisImpl() {}

	@Override
	public void initialize(Optional<TransmissionAnalysis> transmissionAnalysisArg) {
		transmissionAnalysis = transmissionAnalysisArg.get();
	}
	
}
