package com.stargazerproject.bus;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.Event;

/** 
 *  @name 总线非阻塞方法的观测者
 *  @illustrate 总线非阻塞方法的观测者
 *  @author Felixerio
 *  **/
public interface BusObserver<T> {

	public Optional<Boolean> isComplete();
	
	public Optional<T> resultEvent();
	
	public void injectEvent(Optional<Event> event);
	
	public void skip();
	
}
