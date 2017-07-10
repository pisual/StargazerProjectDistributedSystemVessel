package com.stargazerproject.cell.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.stargazerproject.cell.base.impl.CellsMethod;

@Component
@Qualifier("quickPassCell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuickPassCell extends CellsMethod{

	protected QuickPassCell() {
		super(HystrixCommandGroupKey.Factory.asKey("QuickPassCell"));
	}
	
	@Override
	protected Boolean run() throws Exception {
//		System.out.println("QuickPassCell Run");
		return Boolean.TRUE;
	}

}
