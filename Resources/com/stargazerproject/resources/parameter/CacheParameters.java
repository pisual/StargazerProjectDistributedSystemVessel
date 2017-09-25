package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 核心参数列表 CacheParameters
 *  @illustrate 系统所需的CacheParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="cacheParameters")
@Qualifier("cacheParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CacheParameters {
	
	public CacheParameters() {}
	
	//Netty接收Order超时缓存(Google Guava)配置 Start OrderCache
		/** Netty接收Order缓存初始化数目 **/
		/** @illustrate 参数类 **/
		private static final String OrderCache_initialSize = "65536";
		/** 拆分Order缓存最大数目 **/
		/** @illustrate 参数类 **/
		private static final String OrderCache_maxSize = "65537";
		/** 拆分Order缓存 并行级别数目 **/
		/** @illustrate 参数类 **/
		private static final String OrderCache_concurrencyLevel = QueueParameters.Receive_Event_Number_of_consumers;
		/** 拆分Order缓存 非写销毁时间 **/
		/** @illustrate 参数类 **/
		private static final String OrderCache_expireAfterWriteTime = "6553";
		/** 拆分Order缓存 非读销毁时间 **/
		/** @illustrate 参数类 **/
		private static final String OrderCache_expireAfterReadTime = "5553";
		//Netty接收Order超时缓存(Google Guava)配置 End
		
}
