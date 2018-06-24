package com.stargazerproject.messagequeue;

/** 
 *  @name 消息队列接口
 *  @illustrate 消息队列接口的顶级接口，包含了消息队列应该包含的所有接口
 *  @param <K> 队列的能推送的值类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface MessageQueue<T> extends MessageQueueAcquire<T>, MessageQueueControl<T>, MessageQueuePush<T>{

}
