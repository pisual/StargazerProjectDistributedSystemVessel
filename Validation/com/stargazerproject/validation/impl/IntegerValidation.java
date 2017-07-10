package com.stargazerproject.validation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.stargazerproject.validation.NumericalCheck;

/** 
 *  @name Integer类型校验
 *  @illustrate 对Integer类型进行校验，包含了基本的Object校验方法
 *  @author Felixerio
 *  **/
@Component
@Qualifier("integerValidation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IntegerValidation extends ObjectValidation implements NumericalCheck{
	
	/** @construction 初始化构造 **/
	private IntegerValidation() {}
	
	@Override
	/** @illustrate 比较 source 是否大于 lessNumber **/
	public IntegerValidation greatEqualTo(int source, int lessNumber){
		noNull(source);
		Preconditions.checkArgument(source>=lessNumber, source+" Not Great Equal To LessNumber: "+lessNumber);
		return this;
	}

	@Override
	/** @illustrate 比较 source 是否小于 greatNumber **/
	public IntegerValidation lessEqualTo(int source, int greatNumber){
		noNull(source);
		Preconditions.checkArgument(source<=greatNumber, source+" Not Less Equal To LessNumber: "+ greatNumber);
		return this;
	}
	
}
