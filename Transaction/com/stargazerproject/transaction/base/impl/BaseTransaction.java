package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.transaction.Transaction;

public class BaseTransaction extends ID implements Transaction{

	private static final long serialVersionUID = -3595408225181602700L;
	
	protected Transaction transaction;

	@Override
	public void transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis) {
		transaction.transactionAssemble(transactionAssembleAnalysis);
	}

	@Override
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysis) {
		transaction.transactionResult(transactionResultAnalysis);
	}

	@Override
	public void transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis) {
		transaction.transactionExecute(transactionExecuteAnalysis);
	}
	
}
