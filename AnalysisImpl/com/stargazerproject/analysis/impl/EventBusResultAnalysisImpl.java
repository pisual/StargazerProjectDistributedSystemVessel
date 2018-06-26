package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.base.impl.BaseEventBusResultAnalysisImpl;
import com.stargazerproject.analysis.extend.EventBusResultAnalysisExtend;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventBusResultAnalysisImpl")
@Qualifier("eventBusResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusResultAnalysisImpl extends BaseEventBusResultAnalysisImpl implements StanderCharacteristicShell<EventBusResultAnalysisExtend>{

	@Override
	public void initialize(Optional<EventBusResultAnalysisExtend> eventBusResultAnalysisExtendArg) {
		eventBusResultAnalysisExtend = eventBusResultAnalysisExtendArg.get();
	}
	
}
