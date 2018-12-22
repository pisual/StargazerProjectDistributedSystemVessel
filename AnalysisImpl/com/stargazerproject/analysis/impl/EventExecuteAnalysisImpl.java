package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventExecuteAnalysisImpl")
@Qualifier("eventExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventExecuteAnalysisImpl extends BaseEventExecuteAnalysisImpl implements StanderCharacteristicShell<EventExecuteAnalysis>{

	@Override
	public void initialize(Optional<EventExecuteAnalysis> eventExecuteAnalysisArg) {
		eventExecuteAnalysis = eventExecuteAnalysisArg.get();
	}

}
