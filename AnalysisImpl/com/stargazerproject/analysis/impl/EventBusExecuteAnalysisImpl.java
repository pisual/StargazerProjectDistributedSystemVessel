package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.base.impl.BaseEventExecuteAnalysisImpl;
import com.stargazerproject.analysis.extend.EventExecuteAnalysisExtend;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="eventBusExecuteAnalysisImpl")
@Qualifier("eventBusExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusExecuteAnalysisImpl extends BaseEventExecuteAnalysisImpl implements StanderCharacteristicShell<EventExecuteAnalysisExtend>{

	@Override
	public void initialize(Optional<EventExecuteAnalysisExtend> eventExecuteAnalysisExtendArg) {
		eventExecuteAnalysisExtend = eventExecuteAnalysisExtendArg.get();
	}
	
}