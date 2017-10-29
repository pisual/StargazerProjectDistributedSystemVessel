package com.stargazerproject.validation;

/** 
 *  @name 数值类型校验
 *  @illustrate 对数值类型进行校验的基本方法
 *  @param <K> 需要校验的类型
 *  @author Felixerio
 *  **/
public interface StringCheck extends ObjectCheck{
	
	public StringCheck checkLenghtGreaterZero(final String source);
	
	public StringCheck maxLenght(final String source, final int maxLenght);
}
