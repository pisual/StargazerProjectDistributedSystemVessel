package com.stargazerproject.interfaces.characteristic.shell;

import com.google.common.base.Optional;

/** 
 *  @name 特征壳式(®Cells System)领域对象(Shell Type Domain Objects)
 *  @illustrate 采用AOP注入特征的壳式(®Cells System)领域对象(Shell Type Domain Objects),
 *              针对于特征的复杂操作及初始化操作放置在AOP中进行操作。
 *  @param <K> 特征的类型
 *  @author Felixerio
 *  **/
public interface StanderCharacteristicShell<K> {
	
	/**
	* @name 初始化特征
	* @illustrate 通过切面来进行复杂特征的注入，使领域对象的局部特征剥离复杂操作
	* @param <K> 经过Optional包装的特征体
	* @Optional Guava包装
	* **/
	public void initialize(Optional<K> k);
}
