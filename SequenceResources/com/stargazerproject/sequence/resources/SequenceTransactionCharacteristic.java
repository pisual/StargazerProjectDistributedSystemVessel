package com.stargazerproject.sequence.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.sequence.base.impl.SequenceObserverImpl;
import com.stargazerproject.transaction.EventExecute;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.exception.TransactionException;

public class SequenceTransactionCharacteristic implements SequenceTransaction<Transaction>{

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod logMethod;
	
//	@Autowired
//	@Qualifier("eventBusImpl")
//	private Bus<EventExecute> bus;
	
	@Autowired
	@Qualifier("sequenceTransactionCache")
	private Cache<String, Transaction> cache;
	
	@Autowired
	@Qualifier("SequenceBlockTransactionExecuteAnalysis")
	private TransactionExecuteAnalysis sequenceBlockTransactionExecuteAnalysis;
	
	@Autowired
	@Qualifier("SequenceTransactionExecuteAnalysis")
	private TransactionExecuteAnalysis sequenceTransactionExecuteAnalysis;
	
	@Autowired
	@Qualifier("SequenceTransactionResultAnalysis")
	private TransactionResultAnalysis sequenceTransactionResultAnalysis;

	@Override
	public Optional<SequenceTransaction<Transaction>> creatSequence() {
		return Optional.of(this);
	}

	@Override
	public void addSequence(Optional<Transaction> transaction) {
		cache.put(transaction.get().sequenceID(), transaction);
	}

	@Override
	public void clearSequence(Optional<String> transactionID) {
		cache.remove(transactionID);
	}

	@Override
	public void clearSequence() {
		cache.clear();
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startBlockSequence() throws BusEventTimeoutException {
		sequenceRun(Optional.of(sequenceBlockTransactionExecuteAnalysis));
		SequenceObserver<Transaction> sequenceObserver = new SequenceObserverImpl<Transaction, TransactionResultAnalysis>(Optional.of(sequenceTransactionResultAnalysis),Optional.of(transactionList()));
		return Optional.of(sequenceObserver);
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startSequence() {
		sequenceRun(Optional.of(sequenceTransactionExecuteAnalysis));
		SequenceObserver<Transaction> sequenceObserver = new SequenceObserverImpl<Transaction, TransactionResultAnalysis>(Optional.of(sequenceTransactionResultAnalysis),Optional.of(transactionList()));
		return Optional.of(sequenceObserver);
	}
	
	private void sequenceRun(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysisArg){
		cache.entrySet().get().stream().forEach(x -> {
			try {
				x.getValue().transactionExecute(transactionExecuteAnalysisArg);
			} catch (TransactionException e) {
				logMethod.ERROR(x, e.getMessage());
			}
		});
	}
	
	private List<Transaction> transactionList(){
		return cache.entrySet().get().stream().map(x -> x.getValue()).collect(Collectors.toList());
	}
}
