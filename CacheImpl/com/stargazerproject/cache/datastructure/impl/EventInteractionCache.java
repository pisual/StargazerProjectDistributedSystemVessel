package com.stargazerproject.cache.datastructure.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

@Component(value="eventInteractionCache")
@Qualifier("eventInteractionCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class EventInteractionCache extends BaseDataStructureCache<String, String>{

	private static final long serialVersionUID = 7649254860731557694L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventInteractionCache() {}
	
}
