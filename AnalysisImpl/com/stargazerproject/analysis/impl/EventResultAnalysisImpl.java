package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventResultAnalysisImpl")
@Qualifier("eventResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultAnalysisImpl extends BaseEventResultAnalysisImpl implements StanderCharacteristicShell<EventResultAnalysis>{

	@Override
	public void initialize(Optional<EventResultAnalysis> eventResultAnalysisArg) {
		eventResultAnalysis = eventResultAnalysisArg.get();
	}
	
}
