package com.stargazerproject.order.impl.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.ResultResultAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.order.Result;
import com.stargazerproject.order.ResultRecord;
import com.stargazerproject.order.ResultState;

/** 
 *  @name 事件结果（BaseEventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseEventResultShell")
@Qualifier("baseEventResultShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseEventResultShell implements Result, BaseCharacteristic<Result>{

	private static final long serialVersionUID = -4726816340050497590L;

	/**@illustrate 事件结果内容缓存（executionResultCache）中结果标志（ResultState）的Key值**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Order_Event_Result_Map_ExecutionResultCacheResultState;
	
	/**@illustrate 事件结果内容缓存（executionResultCache）中异常信息（ErrorMessage）的Key值**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage;
	
	/**
	* @name 事件结果内容缓存
	* @illustrate 事件结果内容缓存
	* **/
	@Autowired
	@Qualifier("eventResultCache")
	private Cache<String, String> executionResultCache;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventResultShell(Optional<Cache<String, String>> executionResultCacheArg,
			                    Optional<String> Kernel_Order_Event_Result_Map_ExecutionResultCacheResultStateArg,
			                    Optional<String> Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessageArg) {
		executionResultCache = executionResultCacheArg.get();
		Kernel_Order_Event_Result_Map_ExecutionResultCacheResultState = Kernel_Order_Event_Result_Map_ExecutionResultCacheResultStateArg.get();
		Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage = Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessageArg.get();
	}
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BaseEventResultShell(){}
	
	@Override
	public Optional<Result> characteristic() {
		return Optional.of(this);
	}

	/** @illustrate 事件结果内容分析器*/
	@Override
	public void resultResult(ResultResultAnalysis resultResultAnalysis) {
		resultResultAnalysis.analysis(Optional.of(executionResultCache));
	}

	/** @illustrate 设置事件结果标志为完成状态，**/
	@Override
	public Optional<ResultRecord> complete(Optional<ResultState> resultState) {
		executionResultCache.put(Optional.of(Kernel_Order_Event_Result_Map_ExecutionResultCacheResultState), 
				                 Optional.of(ResultState.SUCCESS.toString()));
		return Optional.of(this);
	}

	@Override
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception) {
		addCacheContent(errorMessage.get(), exception.get().fillInStackTrace().toString());
		return null;
	}
	
	/**
	* @name cache Add方法
	* @illustrate 在Cache现有内容上添加新的内容
	* @param Optional<String> errorMessage 异常信息
	* @param Optional<Exception> exception 异常Exception
	* **/
	private void addCacheContent(String errorMessage, String exception){
		Optional<String> cacheErrorMessage = executionResultCache.get(Optional.of("Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage"));
		
		if(cacheErrorMessage.isPresent()){
			cacheErrorMessage = Optional.of(cacheErrorMessage.get() + ";" + errorMessage + ":" + exception);

		}
		else{
			cacheErrorMessage = Optional.of(errorMessage + ":" + exception);
		}
		
		executionResultCache.put(Optional.of(Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage), 
								cacheErrorMessage);

	}
	
	@Override
	public boolean sameValueAs(Result other) {
		return false;
	}

}
