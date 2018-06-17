package com.stargazerproject.cache.datastructure.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="serverListCache")
@Qualifier("serverListCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@NoSpringDepend
public final class ServerListCache extends BaseDataStructureCache<String, List<String>>{

	private static final long serialVersionUID = -3348430545763175902L;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public ServerListCache() {}

}
