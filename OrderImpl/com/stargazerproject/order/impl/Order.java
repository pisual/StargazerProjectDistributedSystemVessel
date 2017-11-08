package com.stargazerproject.order.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.order.Entity;
import com.stargazerproject.order.State;
import com.stargazerproject.util.Clone;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
public final class Order extends ID implements Entity<Order>{
	
	/** @illustrate 指令传输 **/
	private Transmission transmission;
	
	/** @illustrate 指令事务实体**/
	private Transaction transaction;
	
	/** @illustrate 指令级别状态**/
	private State state;
	
	/** @construction 初始化构造 **/
	public Order(Optional<String> idArg, Optional<Transmission> transmissionArg, Optional<Transaction> transactionArg) {
		super(idArg);
		transmission = transmissionArg.get();
	    transaction = transactionArg.get();
	}
	
	public void changeState(Optional<State> stateArg){
		state = stateArg.get();
	}
	
	public Optional<State> state(){
		return Optional.of(state);
	}
	
	/**
	* @name 切分事务
	* @illustrate 切分事务到缓存队列
	* @param Segmentation<Event> 缓存队列
	* **/
	public void segmentation(Optional<Segmentation<Optional<Event>>> segmentation){
		transaction.segmentationMethod(segmentation);
		state = State.Execute;
	}
	
	public Boolean checkResult(){
		return transaction.isComplete();
	}
	
	@Override
	public Order clone() throws CloneNotSupportedException {
		return (Order) Clone.deepClone(Optional.of(this));
	}

	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("transmission", transmission)
                          .add("transaction", transaction)
                          .add("ID", super.IDSequence().get()).toString();
	}
}