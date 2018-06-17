package com.stargazerproject.transaction;

/** 
 *  @name 事件状态
 *  @illustrate 事件状态
 *              INIT ：    初始状态，事件为空
 *              WAIT ：    等待执行状态
	            RUN  ：    运行态
	            PASS ：    异常终止态，跳过此条指令
	            COMPLETE： 正常终止态，事件有可能执行成功或者失败
	            ANALYSIS： 分析终止态，事件已经分析结果
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public enum EventState {
	INIT,
	WAIT,
	RUN,
	PASS,
	COMPLETE,
	ANALYSIS
}
