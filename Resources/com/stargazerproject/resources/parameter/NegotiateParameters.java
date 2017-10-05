package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name negotiateParameters 核心参数列表
 *  @illustrate 系统所需的negotiateParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="negotiateParameters")
@Qualifier("negotiateParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateParameters {
	
	public NegotiateParameters() {}
	
	    //Zookeeper配置 Start
	    /**Zookeeper集群连接主机**/
	    /** @illustrate 参数类 **/
	    private static final String Zookeeeper_Connect_Host = "127.0.0.1:2181";
		/**重新连接策略间隔时间**/
		/** @illustrate 参数类 **/
		private static final String Zookeeeper_Retry_Policy_Base_Sleep_Time_Ms = "1000";
		/**重新连接策略尝试数目**/
		/** @illustrate 参数类 **/
		private static final String Zookeeeper_Retry_Policy_Retry_Connect_Number = "100";
		//Zookeeper配置 End
}
