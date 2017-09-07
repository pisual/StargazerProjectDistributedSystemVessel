package com.stargazerproject.order.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazer.segmentation.Segmentation;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.model.util.Clone;
import com.stargazerproject.order.shared.Entity;

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
	
	public Order() {}
	
	/** @construction 初始化构造 **/
	public Order(Optional<String> idArg, Optional<Transmission> transmissionArg, Optional<Transaction> transactionArg) {
		super(idArg);
		transmission = transmissionArg.get();
	    transaction = transactionArg.get();
	}
	
	/**
	* @name 切分事务
	* @illustrate 切分事务到缓存队列
	* @param Segmentation<Event> 缓存队列
	* **/
	public void segmentation(Segmentation<Optional<Event>> segmentation){
		transaction.segmentationMethod(segmentation);
	}
	
	/**
	* @name 保存Order到临时缓存
	* @illustrate 保存Order到临时缓存
	* @param Cache<String, Order> 缓存
	* **/
	public Order save(Cache<String, Order> cache){
		cache.put(super.IDSequence(), Optional.of(this));
		return this;
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