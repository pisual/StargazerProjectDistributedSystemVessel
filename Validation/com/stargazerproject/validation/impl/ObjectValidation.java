package com.stargazerproject.validation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.stargazerproject.validation.ObjectCheck;

@Component
@Qualifier("objectValidation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ObjectValidation implements ObjectCheck{
	
	protected ObjectValidation() {}

	@Override
	public void noNull(Object objectArg){
		Preconditions.checkNotNull(objectArg,"The Arguement Is Null");
	}
}
