package com.stargazerproject.analysis.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="transactionResultAnalysisImpl")
@Qualifier("transactionResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultAnalysisImpl extends BaseTransactionResultAnalysisImpl implements StanderCharacteristicShell<TransactionResultAnalysis>{

	@Override
	public void initialize(Optional<TransactionResultAnalysis> transactionResultAnalysisArg) {
		transactionResultAnalysis = transactionResultAnalysisArg.get();
	}
	
}
