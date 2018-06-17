package com.stargazerproject.transaction;

import java.io.Serializable;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name 实体
 *  @illustrate 实体对象的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface Entity<T> extends Serializable{
	
	/**
	* @illustrate  注入ID
	* @param Optional<T> idArg ，注入的id对象，不允许空值
	* @ThreadSafeMethodsLevel injectSequenceID的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	* **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public void injectSequenceID(Optional<T> idArg);
	
	/**
	* @illustrate  获取ID
	* @return Optional<T> sequenceID ： ID值
	* @ThreadSafeMethodsLevel sequenceID在初始化状态时的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，
	*                         一旦调用injectSequenceID方法后将锁定Entity对象，这时候Entity的线程安全级别将升级为ThreadSafeLevel.Immutable（不可变对象）,线程安全
	* **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<T> sequenceID();

}
