package com.stargazerproject.sequence.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.sequence.SequenceMethod;

public abstract class BaseSequenceModel implements SequenceMethod{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	private boolean waitmethod = Boolean.FALSE;
	
	protected void methodPass(Optional<String> waitPoint){
		systemParameter.put(waitPoint, Optional.of("Continue"));
	}

	@Override
	public void waitMethod() {
		waitmethod = Boolean.TRUE;
	}

	@Override
	public Optional<Boolean> getWaitMethod() {
		return Optional.of(waitmethod);
	}
}
