package com.stargazerproject.messagequeue.model;

import com.google.common.base.Optional;

/** 
 *  @name 携带者（数据传输单元）
 *  @illustrate 数据传输单元
 *  @param <T> 携带的数据单元类型
 *  @author Felixerio
 *  **/
public class MessageQueueCarry<T> {
	
	private MessageQueueTransmission messageQueueTransmission;
	
	private T carry;
	
	public MessageQueueCarry(Optional<T> carryArg, Optional<MessageQueueTransmission> messageQueueTransmissionArg) {
		carry = carryArg.get();
		messageQueueTransmission = messageQueueTransmissionArg.get();
	}
	
}
