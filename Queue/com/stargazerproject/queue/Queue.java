package com.stargazerproject.queue;

/** 
 *  @name 队列接口
 *  @illustrate 队列的基础功能
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public interface Queue<E> extends QueueControl<E>, QueueProducer<E>{
}
