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

@Component(value="eventExecute")
@Qualifier("eventExecute")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventExecuteImpl implements EventExecute{
	
	@Override
	public Boolean executeEvent(Optional<Cache<String, String>> parameter) {
		CellsTransaction<String, String> cellsTransaction = BeanContainer.instance().getBean(Optional.of("standardCellsTransactionImpl"),CellsTransaction.class);
		cellsTransaction.method(parameter);
		return Boolean.TRUE;
	}
	

}
