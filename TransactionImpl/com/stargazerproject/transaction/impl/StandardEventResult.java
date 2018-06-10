package com.stargazerproject.transaction.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Result;
import com.stargazerproject.transaction.base.impl.BaseEventResult;

@Component(value="standardEventResult")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("standardEventResult")
public class StandardEventResult extends BaseEventResult implements StanderCharacteristicShell<Result>{

	private static final long serialVersionUID = -5034703691118660213L;

	@Override
	@Qualifier("standardEventResultShell")
	@Autowired
	public void initialize(Optional<Result> resultArg) {
		result = resultArg.get();
	}

}
