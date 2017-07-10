package com.stargazerproject.log.collocation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.stargazerproject.log.LogMethod;

public class HystrixClassTest extends HystrixCommand{
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod logMethod;

	protected HystrixClassTest(HystrixCommandGroupKey group) {
		super(group);
	}

	@Override
	protected String run() throws Exception {
		return null;
	}

}
