package com.stargazerproject.transaction;

import java.io.Serializable;

/** 
 *  @name 值对象
 *  @illustrate 值对象（不可追踪的对象）基本功能
 *  @param <T> 值对象类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface ValueObject<T> extends Serializable {
	
	/**
	* @name 值对象比对
	* @illustrate 值对象比对
	* @param <T> 需要比对的值对象
	* @return 比对结果
	* **/
	public boolean sameValueAs(T other);

}
