package com.stargazerproject.sequence;

import com.google.common.base.Optional;

/** 
 *  @name 顺序序列（单向序列）的事务接口
 *  @illustrate 
 *            名词解析：
 *                     事务组：顺序序列的事务载体，默认并且是最完全的的实现是Order，所以，以下的事务组默认为Order
 *                     事务：  事务组（Order）内部包含的一个事务
 *                     
 *              顺序序列的执行方法：
 *                  顺序序列是单次运行方法，首先创建序列，并为序列加入事务组（Order），阻塞序列一经启动，首先会关闭序列，禁止任何对队列的操作，
 *                  随后，严格按照序列顺序，依次执行序列方法事务组，一旦其中任何一个事务执行出现异常，会立即终止序列的继续运行并返回相应结果。
 *                  一旦此次序列执行完毕，会清空并结束序列。
 *              
 *              序列的顺序处理方法为：
 *                  依赖 ：SequenceBus
 *                      
 *                  Parallel Sequence : { Order（"Sequence a","Sequence b","Sequence c"）- Order（"Sequence d","Sequence e","Sequence f"）}
 *                      
 *                  首先，会取出Parallel Sequence中的第一个事务，推入SequenceBus，确认执行完毕并且成功后再取出第二个推入SequenceBus，依次完成。
 *             
 *             顺序序列的使用范围：
 *                 需要严格依赖前者执行的事务
 *                     
 *                     
 *  @param <K> 序列事务组类型
 *  @author Felixerio
 *  **/
public interface SequenceTransaction<K>{
	
	/**
	* @name 创建顺序序列
	* @illustrate 创建顺序序列
	* **/ 
	public Optional<ParallelSequenceTransaction<K>> creatSequence();
	
	/**
	* @name 添加事务组 Order到顺序序列
	* @illustrate 添加事务组 Order
	* @param Optional<K> order 事务组
	* **/ 
	public void addSequence(Optional<K> order);
	
	/**
	* @name 清除Sequence序列中指定的事务组
	* @illustrate 清除Sequence序列的指定事务组
	* @param Optional<String> OrderID 需要清除的事务组的ID
	* **/
	public void clearSequence(Optional<String> orderID);
	
	/**
	* @name 清除Sequence队列
	* @illustrate 清除Sequence序列已经添加的的全部事务组
	* **/
	public void clearSequence();
	
	/**
	* @name 启动顺序序列
	* @illustrate 启动指定的Sequence队列，并阻塞，直到序列全部完成后返回SequenceResult结果
	* **/
	public Optional<SequenceResult<K>> startBlockSequence();
	
	/**
	* @name 启动顺序序列
	* @illustrate 启动指定的Sequence队列，非阻塞，执行后立即返回SequenceObserver结果观察对象，可以根据SequenceObserver来判断
	*             序列是否完成
	* **/
	public Optional<SequenceObserver<K>> startSequence();
}
