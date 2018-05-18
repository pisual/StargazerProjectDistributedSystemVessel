package com.stargazerproject.order;

/** 
 *  @name 事件状态
 *  @illustrate 事件状态
 *              WAIT ：    初始态，等待执行状态
	            RUN  ：    运行态
	            PASS ：    异常终止态，因为运行时出现异常，跳过此条指令
	            COMPLETE： 正常终止态，事件有可能执行成功或者失败
 *  @author Felixerio
 *  @version 1.0
 *  **/
public enum EventState {
	WAIT,
	RUN,
	PASS,
	COMPLETE
}
