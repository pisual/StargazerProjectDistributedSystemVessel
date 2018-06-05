package com.stargazerproject.order.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.order.Event;
import com.stargazerproject.order.base.impl.BaseEvent;
import com.stargazerproject.order.base.impl.ID;

@Component(value="transaction")
@Qualifier("transaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class Transaction extends ID{
	
	private Event[] events;
	
	public Transaction(Optional<String> idArg, @SuppressWarnings("unchecked") ) {
		super(idArg);

	}
	
	public void transactionInitialization(Optional<Event> ... eventsArg){
		events = eventsArg.;
	}
	
	/**
	* @name 事务结果分析，分析者接口
	* @illustrate 事务结果分析，分析者接口
	* @param Optional<TransactionResultAnalysis> 事务结果分析接口
	* **/
	public void transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysisArg){
		transactionResultAnalysisArg.get().analysis(Optional.of(events));
	}
	
	/**
	* @name 启动事务，运行着接口
	* @illustrate 启动事务，运行着接口
	* @param Optional<TransactionAnalysis> 事务运行器接口
	* **/
	public void startTransaction(Optional<TransactionAnalysis> transactionAnalysis){
		transactionAnalysis.get().analysis(Optional.of(events));
	}
	
	@Override
	public String toString() {
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
		for (int j = 0; j < events.length; j++) {
			toStringHelper.add("Events Number: "+ j + " : ", events[j].toString());
		}
        return toStringHelper.toString();
	}
	
}