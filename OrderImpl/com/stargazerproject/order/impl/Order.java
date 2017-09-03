package com.stargazerproject.order.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.model.util.Clone;
import com.stargazerproject.order.shared.Entity;

/** 
 *  @name 缓存接口
 *  @illustrate 实现缓存的基础功能
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@SuppressWarnings("unused")
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
	
	@Override
	public Order clone() throws CloneNotSupportedException {
		return (Order) Clone.deepClone(Optional.of(this));
	}

	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("transmission", transmission)
                          .add("transaction", transaction)
                          .add("ID", id).toString();
	}
}