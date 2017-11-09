package com.stargazerproject.bus;

import com.google.common.base.Optional;

/** 
 *  @name 总线非阻塞方法的观测者
 *  @illustrate 总线非阻塞方法的观测者
 *  @author Felixerio
 *  **/
public interface BusObserver<T> {

	public boolean isComplete();
	
	public Optional<T> resultEvent();
	
	public void skip();
	
}
