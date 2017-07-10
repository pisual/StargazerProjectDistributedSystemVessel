package com.stargazerproject.messagequeue;

public interface MessageQueue<T> extends MessageQueueAcquire<T>, MessageQueueControl<T>, MessageQueuePush<T>{

}
