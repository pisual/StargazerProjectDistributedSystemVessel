 package com.stargazerproject.order.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAnalysis;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.EventResult;
import com.stargazerproject.order.EventAssemble;
import com.stargazerproject.order.EventExecute;
import com.stargazerproject.order.base.impl.ID;

@Component(value="transaction")
@Qualifier("transaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Transaction extends ID{
	
	private static final long serialVersionUID = 5579247376914613210L;
	
	private List<Event> eventsList;
	
	protected Transaction() {}
	
	/**
	* @name 事件生产，生产者调用
	* @illustrate 事件生产，生产者调用
	* @param Optional<TransactionAssembleAnalysis> 事务生产分析接口
	* **/
	public void transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis){
		List<EventAssemble> eventAssembleList = eventsList.stream().map(x -> (EventAssemble)x).collect(Collectors.toList());
		transactionAssembleAnalysis.get().analysis(Optional.of(eventAssembleList));
	}
	
	/**
	* @name 事务结果分析，分析者接口
	* @illustrate 事务结果分析，分析者接口
	* @param Optional<TransactionResultAnalysis> 事务结果分析接口
	* **/
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysisArg){
		List<EventResult> eventAnalyzeList = eventsList.stream().map(x -> (EventResult)x).collect(Collectors.toList());
		transactionResultAnalysisArg.get().analysis(Optional.of(eventAnalyzeList));
	}
	
	/**
	* @name 启动事务，运行者接口
	* @illustrate 启动事务，运行者接口
	* @param Optional<TransactionAnalysis> 事务运行分析接口
	* **/
	public void startTransaction(Optional<TransactionAnalysis> transactionAnalysis){
		List<EventExecute> eventExecuteList = eventsList.stream().map(x -> (EventExecute)x).collect(Collectors.toList());
		transactionAnalysis.get().analysis(Optional.of(eventExecuteList));
	}
	
	@Override
	public String toString() {
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
		eventsList.forEach(x -> toStringHelper.add("Events : ", x.toString()));
        return toStringHelper.toString();
	}
	
}