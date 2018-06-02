package com.stargazerproject.order.base.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.order.Entity;

/** 
 *  @name ID对象（可追踪实体根对象）
 *  @illustrate ID对象是所有可跟踪实体对象的根对象
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component
@Qualifier("iD")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ID implements Entity<String>{
	
	private static final long serialVersionUID = -8739771505481213633L;
	
	/** @illustrate ID**/
	private String id;
	
	protected ID() {}

	public ID(Optional<String> idArg) {
		id = idArg.get();
	}
	
	/**
	* @name 获取ID
	* @illustrate  获取ID
	* @return Optional<String> sequenceID ： ID值
	* **/
	@Override
	public Optional<String> sequenceID(){
		return Optional.of(id);
	}
}
