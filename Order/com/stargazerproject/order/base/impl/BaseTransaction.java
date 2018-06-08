package com.stargazerproject.order.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.order.Transaction;

public class BaseTransaction implements Transaction{

	protected Transaction transaction;

	@Override
	public void eventAssemble(Optional<TransactionAssembleAnalysis> eventAssembleAnalysis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void injectSequenceID(Optional<String> idArg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<String> sequenceID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
