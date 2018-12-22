package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="transactionExecuteAnalysisImpl")
@Qualifier("transactionExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionExecuteAnalysisImpl extends BaseTransactionExecuteAnalysisImpl implements StanderCharacteristicShell<TransactionExecuteAnalysis>{

	@Override
	public void initialize(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysisArg) {
		transactionExecuteAnalysis = transactionExecuteAnalysisArg.get();
	}
	
}
