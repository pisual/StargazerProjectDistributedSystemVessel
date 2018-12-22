package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="transactionAssembleAnalysisImpl")
@Qualifier("transactionAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionAssembleAnalysisImpl extends BaseTransactionAssembleAnalysisImpl implements StanderCharacteristicShell<TransactionAssembleAnalysis>{

	@Override
	public void initialize(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysisArg) {
		transactionAssembleAnalysis = transactionAssembleAnalysisArg.get();
	}

}
