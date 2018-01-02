package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.resources.Parameters;

/** 
 *  @name negotiateParameters 核心参数列表
 *  @illustrate 系统所需的negotiateParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="negotiateParameters")
@Qualifier("negotiateParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="negotiateParameters")
@SuppressWarnings("unused")
public class NegotiateParameters {
	
	public NegotiateParameters() {}
	
	    //Zookeeper配置 Start
	    /** @illustrate Zookeeper集群主机 **/
	    private static final String Kernel_Negotiate_Connection_Host = "127.0.0.1:2181";
		/** @illustrate 重新连接策略间隔时间 **/
		private static final String Kernel_Negotiate_Connection_RetryPolicySleepTime = "1000";
		/**@illustrate 重新连接策略尝试次数**/
		private static final String Kernel_Negotiate_Connection_RetryConnectTime = "20";
		//Zookeeper配置 End
		
	    //Curator配置 Start
	    /** @illustrate 创建锁的超时时间 **/
		private static final String Kernel_Negotiate_Mode_Lock_CreatLockOutTime = "1";
		//Curator配置 End
		
	    //路径配置 Start
	    /** @illustrate 根路径 **/
	    private static final String Kernel_Negotiate_BasePath_RootPath = "/System";
	    /** @illustrate 新生区路径 **/
	    private static final String Kernel_Negotiate_BasePath_EdenNodePath = Kernel_Negotiate_BasePath_RootPath + "/EdenCells";
	    /** @illustrate 建组区路径 **/
	    private static final String Kernel_Negotiate_BasePath_ZoneNodePath = Kernel_Negotiate_BasePath_RootPath + "/ZoneCells";
	    //路径配置 End
}
