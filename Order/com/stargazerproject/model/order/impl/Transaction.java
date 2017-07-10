package com.stargazerproject.model.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.cache.Cache;


@Component
@Qualifier("transaction")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class Transaction extends ID{
	
	@Autowired
	@Qualifier("eventCache")
	/** @illustrate 获取SystemParameterCache(系统参数缓存)接口 **/
	private Cache<Integer, Event> eventCahce;
	
	/** @illustrate Event数目**/
	private final int eventNumber;
	
	public Transaction(Optional<String> idArg, @SuppressWarnings("unchecked") Optional<Event> ... events) {
		super(idArg);
		int tempNumber = 0;
		for(Optional<Event> event:events){
			eventCahce.put(Optional.of(tempNumber++), event);
		}
		eventNumber = tempNumber;
	}
	
	protected void segmentationMethod(Segmentation<Event> segmentation){
		for(int i= eventNumber; i>= 0; i-- ){
			segmentation.batchSegmentation(eventCahce.get(Optional.of(i)).get());
		}
	}
}