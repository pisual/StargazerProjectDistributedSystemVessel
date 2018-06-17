package com.stargazerproject.transaction;

/** 
 *  @name 事务（Transaction）接口
 *  @illustrate 事务的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface Transaction extends TransactionAssemble, 
                                     TransactionResult, 
                                     TransactionExecute, 
                                     Entity<String>{
}
