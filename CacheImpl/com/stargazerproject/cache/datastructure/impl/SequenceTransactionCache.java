package com.stargazerproject.cache.datastructure.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;
import com.stargazerproject.transaction.Transaction;

@Component(value="sequenceTransactionCache")
@Qualifier("sequenceTransactionCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class SequenceTransactionCache extends BaseDataStructureCache<String, Transaction>{

	private static final long serialVersionUID = 7876165181093327466L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SequenceTransactionCache() {}
	
}
