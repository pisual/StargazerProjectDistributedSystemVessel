package com.stargazerproject.transaction.impl.resources.shell;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventAssemble;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.base.impl.ID;

/** 
 *  @name 事务（baseTransaction）实现
 *  @illustrate 事务（baseTransaction）是事件的原子聚合体
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseTransactionShell")
@Qualifier("baseTransactionShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseTransactionShell extends ID implements Transaction, BaseCharacteristic<Transaction>{
 
private static final long serialVersionUID = 5579247376914613210L;
	
	private Collection<Event> eventsList;
	
	protected BaseTransactionShell() {}
	
	@Override
	public Optional<Transaction> characteristic() {
		return Optional.of(this);
	}
	
	/**
	* @name 事件生产，生产者调用
	* @illustrate 事件生产，生产者调用
	* @param Optional<TransactionAssembleAnalysis> 事务生产分析接口
	* **/
	@Override
	public void transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis){
		List<EventAssemble> eventAssembleList = eventsList.stream().map(x -> (EventAssemble)x).collect(Collectors.toList());
		transactionAssembleAnalysis.get().analysis(Optional.of(eventAssembleList));
	}
	
	/**
	* @name 事务结果分析，分析者接口
	* @illustrate 事务结果分析，分析者接口
	* @param Optional<TransactionResultAnalysis> 事务结果分析接口
	* **/
	@Override
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysisArg){
		List<EventResult> eventAnalyzeList = eventsList.stream().map(x -> (EventResult)x).collect(Collectors.toList());
		transactionResultAnalysisArg.get().analysis(Optional.of(eventAnalyzeList));
	}
	
	/**
	* @name 启动事务，运行者接口
	* @illustrate 启动事务，运行者接口
	* @param Optional<TransactionExecuteAnalysis> 事务运行分析接口
	* **/
	@Override
	public void transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis) {
		List<Event> eventExecuteList = eventsList.stream().map(x -> (Event)x).collect(Collectors.toList());
		transactionExecuteAnalysis.get().analysis(Optional.of(eventExecuteList));
	}
	
	/**
	* @name 跳过此事务，通过调用其名下的Event的skipEvent方法来主动放弃Event的执行
	* @illustrate 跳过此事务，通过调用其名下的Event的skipEvent方法来主动放弃Event的执行
	* @param Optional<TransactionExecuteAnalysis> 事务运行分析接口
	* **/
	@Override
	public void skipTransaction() {
		eventsList.forEach(x -> x.skipEvent());
	}
	
	@Override
	public String toString() {
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
		eventsList.forEach(x -> toStringHelper.add("Events : ", x.toString()));
        return toStringHelper.toString();
	}

	
}
