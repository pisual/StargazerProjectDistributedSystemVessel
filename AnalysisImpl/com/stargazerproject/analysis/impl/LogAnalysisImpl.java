package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.LogAnalysis;
import com.stargazerproject.analysis.base.impl.BaseLogAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="logAnalysisImpl")
@Qualifier("logAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogAnalysisImpl extends BaseLogAnalysisImpl implements StanderCharacteristicShell<LogAnalysis>{

	@Override
	public void initialize(Optional<LogAnalysis> logAnalysisArg) {
		logAnalysis = logAnalysisArg.get();
	}


}
