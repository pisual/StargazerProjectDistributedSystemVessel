package com.stargazerproject.sequence;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

public interface SequenceTransaction<K>{
	
	/**
	* @name 添加事务序列Order
	* @illustrate 添加事务order
	* @param Optional<K> order 序列组
	* **/ 
	public void addSequence(Optional<K> order);
	
	/**
	* @name 清除指定的Sequence队列
	* @illustrate 清除指定的Sequence队列
	* @param Optional<String> OrderID Order的ID
	* **/
	public void clear(Optional<String> orderID);
	
	/**
	* @name 启动指定的Sequence队列
	* @illustrate 启动指定的Sequence队列
	* @param Optional<String> OrderID Order的ID
	* **/
	public void startSequence(Optional<String> orderID) throws BusEventTimeoutException;
}
