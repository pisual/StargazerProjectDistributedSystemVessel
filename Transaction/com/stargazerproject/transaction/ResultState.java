package com.stargazerproject.transaction;

/** 
 *  @name 结果状态
 *  @illustrate 结果状态
 *  
 *         	    SUCCESS : 成功，代表执行是按照预期执行，并且没有抛出异常
	            FAULT   : 失败，执行出现错误，没有按照预期执行
	            WAIT    : 等待，等待执行
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public enum ResultState {
	SUCCESS,
	FAULT,
	WAIT
}
