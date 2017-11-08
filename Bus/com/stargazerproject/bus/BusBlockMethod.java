package com.stargazerproject.bus;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

/** 
 *  @name 总线阻塞方法
 *  @illustrate 总线阻塞方法
 *  @param <T>  总线消息德类型
 *  @author Felixerio
 *  **/
public interface BusBlockMethod<T> {

	/**
	* @name 置入
	* @illustrate 阻塞置入方法，将阻塞直到结果返回
	* @param <T> 指令
	* @return 返回指令
	* **/
	public Optional<T> push(Optional<T> busEvent, TimeUnit timeUnit, int timeout)throws BusEventTimeoutException;
	
}
