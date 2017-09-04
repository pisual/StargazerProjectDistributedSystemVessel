package com.stargazerproject.order.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;

@Component
@Qualifier("transaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class Transaction extends ID{
	
	private Optional<Event>[] events;
	
	public Transaction(Optional<String> idArg, @SuppressWarnings("unchecked") Optional<Event> ... eventsArg) {
		super(idArg);
		events = eventsArg;
	}
	
	public Boolean isComplete(){
		boolean result = true;
		for(int i= events.length; i>= 0; i-- ){
			result = result & events[i].get().isComplete();
		}
		return result;
	}
	
	public void segmentationMethod(Segmentation<Event> segmentation){
		for(int i= events.length; i>= 0; i-- ){
			segmentation.batchSegmentation(events[i].get());
		}
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