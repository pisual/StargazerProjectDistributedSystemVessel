package com.stargazerproject.messagequeue;

import com.google.common.base.Optional;

/** 
 *  @name 消息队列接口
 *  @illustrate 实现消息队列的控制功能
 *  @author Felixerio 
 *  @NetWeave NetWeave://Join/127.0.0.1:10491/StargazerSystem/cells/tasks/test1|StargazerSystem/cells/server/test2|
 *  **/
public interface MessageQueueControl<T> {
	
	/**
	* @name 加入消息队列
	* @illustrate 加入消息队列
	* **/
	public void join(Optional<String> messageQueueUrl);
	
	/**
	* @name 退出消息队列
	* @illustrate 退出消息队列
	* **/
	public void out();

}
