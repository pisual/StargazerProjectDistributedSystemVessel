package com.stargazerproject.cache.datastructure.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

/** 
 *  @name 事件（BaseEvent）结果（ResultRecord）专用Cache
 *  @illustrate 事件（BaseEvent）结果专用Cache，用于初始化事件结果缓存的一些必要内容，
 *              EventResultCache需要初始化一些必要的键值对，初始化这些键值对需要在注解@NeedInitialization的参数中进行声明，
 *              注解@NeedInitialization的参数content（内容）中的键值对的声明方法为Json格式{"key1":"value","key2":"value"}
 *  @param <String> 缓存的Key值类型
 *  @param <String> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="eventResultCache")
@Qualifier("eventResultCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NeedInitialization(content = "{'ResultRecord' : 'Faile'," 	  /** @illustrate 结果状态（"Faile" Or "Success"） **/
		                    + " 'Exception' : 'NULL'"      /** @illustrate 异常信息（NULL Or ExceptionMessage） **/
		                    + " 'CompleteTime' : '0'"      /** @illustrate 完成时间（0 Or 格林威治时间（精确到秒）） **/
		                    + " 'RetryTime':'0'}")         /** @illustrate 重试次数（0 Or Int） **/
public final class EventResultCache extends BaseDataStructureCache<String, String>{
		
	private static final long serialVersionUID = 7696574737465211477L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventResultCache() {}

}