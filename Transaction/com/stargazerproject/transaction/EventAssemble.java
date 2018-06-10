package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;

/** 
 *  @name 事件初始化（EventAssemble）接口
 *  @illustrate 事件的初始化基础运行方法
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface EventAssemble {
	
	/** @illustrate 事件生产，生产者调用
	 *  @param      Optional<EventAssembleAnalysis> eventAssembleAnalysis : 事件生产分析器接口
	 * **/
	public void eventAssemble(Optional<EventAssembleAnalysis> eventAssembleAnalysis);
	
}
