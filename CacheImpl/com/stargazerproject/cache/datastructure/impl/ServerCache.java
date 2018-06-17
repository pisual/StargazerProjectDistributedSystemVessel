package com.stargazerproject.cache.datastructure.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="serverCache")
@Qualifier("serverCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@NoSpringDepend
public final class ServerCache extends BaseDataStructureCache<String, Boolean>{

	private static final long serialVersionUID = -9098024223922834602L;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ServerCache() {}

}
