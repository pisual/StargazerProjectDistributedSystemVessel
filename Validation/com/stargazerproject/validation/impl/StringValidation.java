package com.stargazerproject.validation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.stargazerproject.validation.StringCheck;

@Component(value="stringValidation")
@Qualifier("stringValidation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StringValidation extends ObjectValidation implements StringCheck{
	
	private StringValidation() {}
	
	@Override
	public StringValidation checkLenghtGreaterZero(final String source){
		noNull(source);
		Preconditions.checkArgument(source.length()>0);
		return this;
	}
	
	@Override
	public StringValidation maxLenght(final String source, final int maxLenght){
		noNull(source);
		Preconditions.checkArgument(source.length()>0);
		return this;
	}
	
}
