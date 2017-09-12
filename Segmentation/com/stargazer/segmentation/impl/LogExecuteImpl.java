package com.stargazer.segmentation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.LogExecute;
import com.stargazerproject.log.model.LogData;

@Component(value="logExecute")
@Qualifier("logExecute")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogExecuteImpl implements LogExecute{

	@Override
	public Boolean executeLog(Optional<LogData> logData) {
		System.out.println(logData.toString());
		return Boolean.TRUE;
	}

}
