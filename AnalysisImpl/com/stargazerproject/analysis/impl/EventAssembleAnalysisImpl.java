package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventAssembleAnalysisImpl")
@Qualifier("eventAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventAssembleAnalysisImpl extends BaseEventAssembleAnalysisImpl implements StanderCharacteristicShell<EventAssembleAnalysis>{

	@Override
	public void initialize(Optional<EventAssembleAnalysis> eventAssembleAnalysisArg) {
		eventAssembleAnalysis = eventAssembleAnalysisArg.get();
	}
	
}
