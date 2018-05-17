package com.stargazerproject.sequence;

import com.google.common.base.Optional;

/** 
 *  @name 并行序列的事务接口
 *  @illustrate 
 *             名词解析：
 *                     事务组（Order）：并行序列的事务载体，默认并且是最完全的的实现是Order，所以，以下的事务组（Order）默认为Order实现
 *                     事务：  事务组（Order）内部包含的一个事务
 *    
 *              并行序列的执行方法：
 *                  并行序列的事务接口方法，并行序列是单次运行方法，首先创建序列，并为序列加入事务组（Order），并行序列一经启动，首先会关闭序列，禁止任何对队列的操作，
 *                  随后，会同时启动预先注入的所有序列组（Order），其中任何事务的执行失败结果（包含超时或其它异常）会被记录到事务组（Order）中，但不会影响其它事务的运行，
 *                  因为序列所依赖的Bus的并行处理数目是有限制的，所以，虽然序列已经全部启动，但是，其同时处理的数目还是有限制的。
 *                  一旦此次序列执行完毕，会清空并结束序列。
 *              
 *              序列的并行处理方法为：
 *                  依赖 ：SequenceBus（例如 最大并行数目 为 4）
 *                      
 *                  Parallel Sequence : { Order（"Sequence a","Sequence b","Sequence c"）- Order（"Sequence d","Sequence e","Sequence f"）}
 *                      
 *                  首先，会把Parallel Sequence组内所有的序列内容按照顺序全部推送到SequenceBus，然后SequenceBus会启动4个并行处理器开始执行，
 *                  随着开始执行，因为事务执行时间不同的关系，原有的序列内容顺序会打乱。
 *             
 *             并行序列的使用范围：
 *                 单独无相互依赖的序列方法
 *                     
 *                     
 *  @param <K> 序列事务组（Order）类型
 *  @author Felixerio
 *  **/
public interface ParallelSequenceTransaction<K>{
	
	/**
	* @name 创建并行序列
	* @illustrate 创建并行序列
	* **/ 
	public Optional<ParallelSequenceTransaction<K>> creatParallelSequence();
	
	/**
	* @name 添加事务组 Order到并行序列
	* @illustrate 添加事务组 Order
	* @param Optional<K> order 事务组
	* **/ 
	public void addParallelSequence(Optional<K> order);
	
	/**
	* @name 清除Sequence序列中指定的事务组
	* @illustrate 清除Sequence序列的指定事务组
	* @param Optional<String> OrderID 需要清除的事务组的ID
	* **/
	public void clearParallelSequence(Optional<String> orderID);
	
	/**
	* @name 清除Sequence队列
	* @illustrate 清除Sequence序列已经添加的的全部事务组
	* **/
	public void clearParallelSequence();
	
	/**
	* @name 启动并行序列
	* @illustrate 启动指定的Sequence队列，并阻塞，直到序列全部完成后返回ParallelSequenceResult结果
	* **/
	public Optional<SequenceResult<K>> startBlockParallelSequence();
	
	/**
	* @name 启动并行序列
	* @illustrate 启动指定的Sequence队列，非阻塞，执行后立即返回ParallelSequenceObserver结果观察对象，可以根据ParallelSequenceObserver来检测
	*             序列是否完成
	* **/
	public Optional<SequenceObserver<K>> startParallelSequence();
	
	/**
	* @name 终止并行序列
	* @illustrate 尽力终止并行序列是一个没有确定结果的不推荐使用方法，它为并行序列提供一个可以紧急终止的方法，
	*             一旦调用此方法，会把本序列的事务组内部的全部事务置于Skip状态，由于遍历全部事务是需要时间的，
	*             因为并行序列是一次性推送所有事务进入到事件总线，一旦事务在置于Skio状态前就已经进入了执行状态中，
	*             那么这个事务的Skip是无效的，所以，终止并行序列方法是一个没有确切结果的方法，只是尽力使得还没有执行的方法在事件总线中跳过。
	* **/
	public Optional<SequenceObserver<K>> shutDownParallelSequence();
	
}
