package com.stargazerproject.cell.base.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public abstract class CellsMethod extends HystrixCommand<Boolean>{

	protected CellsMethod(HystrixCommandGroupKey group) {
		super(group);
	}

}
