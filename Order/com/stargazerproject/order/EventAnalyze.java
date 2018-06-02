package com.stargazerproject.order;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;

/** 
 *  @name 事件（EventAnalyze）接口
 *  @illustrate 事件的基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventAnalyze {
	
	/** @illustrate 分析事件结果，分析者调用
	 *  @param      Optional<EventResultAnalysis> eventResultAnalysis : 事件结果分析器接口
	 * **/
	public void eventResult(Optional<EventResultAnalysis> eventResultAnalysis);
	
}
