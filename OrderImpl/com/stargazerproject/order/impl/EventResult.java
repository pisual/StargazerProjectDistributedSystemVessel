package com.stargazerproject.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.ResultAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.order.Result;
import com.stargazerproject.order.ResultRecord;
import com.stargazerproject.order.ResultState;


/** 
 *  @name 事件结果（EventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public final class EventResult implements Result{
	
	/**@illustrate 事件结果内容缓存（executionResultCache）中结果标志（ResultState）的Key值**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Order_Event_Result_Map_ExecutionResultCacheResultState;
	
	/**@illustrate 事件结果内容缓存（executionResultCache）中异常信息（ErrorMessage）的Key值**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Order_Event_Result_Map_ExecutionResultCacheErrorMessage;
	
	
	/** @illustrate 事件结果内容缓存**/
	private Cache<String, String> executionResultCache;
	
	/** @illustrate EventResult的构造函数，需要注入Optional<Cache<String, String>>的一个实现作为运行结果缓存(execution ResultRecord Cache)**/
	public EventResult(Optional<Cache<String, String>> executionResultCacheArg) {
		executionResultCache = executionResultCacheArg.get();
	}

	/** @illustrate 事件结果内容分析器*/
	@Override
	public void resultAnalyze(ResultAnalysis resultAnalysis) {
		resultAnalysis.analysis(Optional.of(executionResultCache));
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

}