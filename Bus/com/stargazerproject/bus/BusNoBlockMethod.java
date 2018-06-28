package com.stargazerproject.bus;

import com.google.common.base.Optional;

/** 
 *  @name 总线非阻塞方法
 *  @illustrate 总线非阻塞方法
 *  @author Felixerio
 *  **/
public interface BusNoBlockMethod<T> {
	
	/**
	* @name 置入
	* @illustrate 非阻塞置入方法，可以根据BusObserver查询指令是否完成
	* @param <BaseEvent> 事件
	* @return 返回指令
	* **/
	public Optional<BusObserver<T>> pushNoBlock(Optional<T> busEvent);
	
}
