package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.resources.annotation.Parameters;

/** 
 *  @name Order参数列表
 *  @illustrate Order服务组件参数映射列表
 *  @author Felixerio
 *  **/
@Component(value="orderParameters")
@Qualifier("orderParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="orderParameters")
@SuppressWarnings("unused")
public class OrderParameters {
	
	public OrderParameters() {}
		
	//Order参数列表 Start
	/**@illustrate 事件结果内容缓存（executionResultCache）中结果标志（ResultState）的Key值**/
	private static final String Kernel_Order_Event_Result_Map_ExecutionResultCacheResultState = "ResultState";
	
	/**@illustrate 事件结果内容缓存（executionResultCache）中异常信息（ErrorMessage）的Key值**/
	private static final String Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage = "ErrorMessage";
	
	//Order参数列表 End
		
}
