package com.stargazerproject.order.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.Transaction;
import com.stargazerproject.order.base.impl.BaseTransaction;

@Component(value="standardTransaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("standardTransaction")
public class StandardTransaction extends BaseTransaction implements StanderCharacteristicShell<Transaction>{

	private static final long serialVersionUID = 422476986033443667L;

	@Override
	public void initialize(Optional<Transaction> transactionArg) {
		transaction = transactionArg.get();
	}
	

}
