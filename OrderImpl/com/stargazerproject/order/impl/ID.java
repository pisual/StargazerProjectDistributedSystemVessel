package com.stargazerproject.order.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

/** 
 *  @name 实体ID
 *  @illustrate ID序列
 *  @author Felixerio
 *  **/
@Component
@Qualifier("iD")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ID{

	/** @illustrate ID**/
	private String id;
	
	protected ID() {}

	public ID(Optional<String> idArg) {
		id = idArg.get();
	}
	
	public Optional<String> IDSequence(){
		return Optional.of(id);
	}
}
