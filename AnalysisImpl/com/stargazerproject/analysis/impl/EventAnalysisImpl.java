package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventAnalysisImpl")
@Qualifier("eventAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventAnalysisImpl extends BaseEventAnalysisImpl implements StanderCharacteristicShell<EventAnalysis>{

	@Override
	public void initialize(Optional<EventAnalysis> eventAnalysisArg) {
		eventAnalysis = eventAnalysisArg.get();
	}

}
