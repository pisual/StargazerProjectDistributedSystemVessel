package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventExecuteAnalysis;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventBusAnalysisImpl")
@Qualifier("eventBusAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusAnalysisImpl extends BaseEventExecuteAnalysis implements StanderCharacteristicShell<EventExecuteAnalysis>{

	@Override
	public void initialize(Optional<EventExecuteAnalysis> eventBusAnalysisArg) {
		eventAnalysis = eventBusAnalysisArg.get();
	}
	
}
