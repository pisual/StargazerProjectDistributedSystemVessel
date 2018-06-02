package com.stargazerproject.order;

import java.io.Serializable;

import com.google.common.base.Optional;

/** 
 *  @name 实体
 *  @illustrate 实体对象的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface Entity<T> extends Serializable{
	
	/**
	* @name 获取ID
	* @illustrate  获取ID
	* @return Optional<T> sequenceID ： ID值
	* **/
	public Optional<T> sequenceID();

}
