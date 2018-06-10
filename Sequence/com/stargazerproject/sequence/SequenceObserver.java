package com.stargazerproject.sequence;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.base.impl.BaseEvent;

/** 
 *  @name 并行序列方法的观测者
 *  @illustrate 并行序列结果分为两级状态
 *                             
 *      序列是否完成                    Complete（True） - OR - NoComplete（False）
 *                                       ／
 *      序列是否成功                  Success （True）- OR - NoSuccess（False）
 *      
 *  @author Felixerio
 *  **/
public interface SequenceObserver<T> {

	public boolean isComplete();
	
	public boolean isSuccess();
	
	public Optional<List<BaseEvent>> failResultList();
	
	public Optional<List<T>> sequenceResultList();
	
}
