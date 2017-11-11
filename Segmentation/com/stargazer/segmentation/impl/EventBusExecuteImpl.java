package com.stargazer.segmentation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazer.segmentation.EventExecute;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="eventBusExecute")
@Qualifier("eventBusExecute")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusExecuteImpl implements EventExecute{
	
	@Override
	public Boolean executeEvent(Optional<Cache<String, String>> parameter) {
		CellsTransaction<String, String> cellsTransaction = BeanContainer.instance().getBean(parameter.get().get(Optional.of("CellsMethodName")), CellsTransaction.class);
		cellsTransaction.method(parameter);
		return Boolean.TRUE;
	}
	

}
