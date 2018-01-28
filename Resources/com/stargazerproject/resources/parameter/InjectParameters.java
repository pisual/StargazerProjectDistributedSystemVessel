package com.stargazerproject.resources.parameter;

import java.io.Serializable;

/** 
 *  @name 外部注入参数通用Class
 *  @illustrate 外部注入参数通用Class
 *  @author Felixerio
 *  **/
@SuppressWarnings("unused")
public class InjectParameters implements Serializable {

	private static final long serialVersionUID = -61107373055806462L;

	/**   测试输入参数，不是真实的参数   **/
	
	//Netty接收Order超时缓存(Google Guava)配置 Start OrderCache
	/** @illustrate Netty接收Order缓存初始化数目 **/
	private static final String Parameters_Module_Kernel_Cache_OrderCache_InitialSize = "65536";
	/** @illustrate 拆分Order缓存最大数目 **/
	private static final String Parameters_Module_Kernel_Cache_OrderCache_MaxSize = "65537";
	/** @illustrate 拆分Order缓存 并行级别数目 **/
	private static final String Parameters_Module_Kernel_Cache_OrderCache_ConcurrencyLevel = QueueParameters.Kernel_Queue_ReceiveEventQueue_Consumer_NumberOfConsumers;
	/** @illustrate 拆分Order缓存 非写销毁时间 **/
	private static final String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterWriteTime = "6553";
	/** @illustrate 拆分Order缓存 非读销毁时间 **/
	private static final String Parameters_Module_Kernel_Cache_OrderCache_ExpireAfterReadTime = "5553";
	//Netty接收Order超时缓存(Google Guava)配置 End
}
