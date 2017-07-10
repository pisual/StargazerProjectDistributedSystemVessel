package com.stargazerproject.validation;



/** 
 *  @name 数值类型校验
 *  @illustrate 对数值类型进行校验的基本方法
 *  @param <K> 需要校验的类型
 *  @author Felixerio
 *  **/
public interface NumericalCheck {
	
	/** @illustrate 比较 source 是否大于 lessNumber **/
	public NumericalCheck greatEqualTo(int source, int lessNumber);
	
	/** @illustrate 比较 source 是否小于 greatNumber **/
	public NumericalCheck lessEqualTo(int source, int greatNumber);
}
