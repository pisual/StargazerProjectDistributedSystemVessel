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

@Component
@Qualifier("transaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class Transaction extends ID{
	
	private Event[] events;
	
	public Transaction(Optional<String> idArg, @SuppressWarnings("unchecked") Optional<BaseEvent> ... eventsArg) {
		super(idArg);
		events = eventsArg;
	}
	
	public void transactionResul(Optional<TransactionResultAnalysis> transactionResultAnalysisArg){
		transactionResultAnalysisArg.get().analysis(Optional.of(events));
	}
	
	public void startTransaction(Optional<TransactionAnalysis> transactionAnalysis){
		transactionAnalysis.get().analysis(Optional.of(events));
	}
	
	@Override
	public String toString() {
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
		for (int j = 0; j < events.length; j++) {
			toStringHelper.add("events"+j, events[j].get());
		}
        return toStringHelper.toString();
	}
	
}