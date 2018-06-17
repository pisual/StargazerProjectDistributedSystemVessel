package com.stargazerproject.cache.datastructure.impl;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="interProcessSemaphoreMutexCache")
@Qualifier("interProcessSemaphoreMutexCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@NoSpringDepend
public final class InterProcessSemaphoreMutexCache extends BaseDataStructureCache<String, InterProcessSemaphoreMutex>{

	private static final long serialVersionUID = -4435329472282199007L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public InterProcessSemaphoreMutexCache() {}
}
