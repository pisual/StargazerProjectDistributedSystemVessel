package com.stargazerproject.bus;

/** 
 *  @name 总线非阻塞方法的监听器
 *  @illustrate 总线非阻塞方法的监听器
 *  @author Felixerio
 *  **/
public interface BusEventListen {

	public void onStart();
	
	public void onCompleted();
	
	public void onError(Throwable e);
	
}
