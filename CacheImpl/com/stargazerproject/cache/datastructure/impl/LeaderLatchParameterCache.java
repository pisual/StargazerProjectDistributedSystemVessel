package com.stargazerproject.cache.datastructure.impl;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="leaderLatchParameterCache")
@Qualifier("leaderLatchParameterCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class LeaderLatchParameterCache extends BaseDataStructureCache<String, LeaderLatch>{
	
	private static final long serialVersionUID = -2669532116874289464L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LeaderLatchParameterCache() {}
	
}
