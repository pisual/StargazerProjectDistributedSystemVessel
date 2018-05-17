package com.stargazerproject.sequence;

import java.util.List;

import com.google.common.base.Optional;

/** 
 *  @name 并行序列方法的观测者
 *  @illustrate 并行序列方法的观测者
 *  @author Felixerio
 *  **/
public interface SequenceResult<T> {

	public boolean isComplete();
	
	public Optional<List<T>> sequenceResultList();
	
}
