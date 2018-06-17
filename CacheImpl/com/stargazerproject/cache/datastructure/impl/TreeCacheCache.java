package com.stargazerproject.cache.datastructure.impl;

import org.apache.curator.framework.recipes.cache.TreeCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="treeCacheCache")
@Qualifier("treeCacheCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class TreeCacheCache extends BaseDataStructureCache<String, TreeCache>{

	private static final long serialVersionUID = -1077479829120345313L;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TreeCacheCache() {}

}
