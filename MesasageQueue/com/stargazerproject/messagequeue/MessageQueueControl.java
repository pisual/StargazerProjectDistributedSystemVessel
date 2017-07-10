package com.stargazerproject.messagequeue;

/** 
 *  @name 消息队列接口
 *  @illustrate 实现消息队列的控制功能
 *  @author Felixerio
 *  **/
public interface MessageQueueControl<T> {
	
	/**
	* @name 加入消息队列
	* @illustrate 同意参与网织：    NetWeave://Join/127.0.0.1:10491/StargazerSystem/cells/tasks/test1|StargazerSystem/cells/server/test2|
	* **/
	public MessageQueueControl<T> Join();

}
