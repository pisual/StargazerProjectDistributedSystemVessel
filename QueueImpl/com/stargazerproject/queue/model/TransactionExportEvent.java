package com.stargazerproject.queue.model;

import com.google.common.base.MoreObjects;
import com.stargazerproject.transaction.Transaction;

/** 
 *  @name lmax disruptor 专用的TransactionModel
 *  @illustrate TransactionExport队列
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class TransactionExportEvent {
	
	private Transaction transaction;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionExportEvent() {}
	
    public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transactionArg) {
		transaction = transactionArg;
	}

	public void clear(){
		transaction = null;
    }
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("Transaction", transaction).toString();
	}

}
