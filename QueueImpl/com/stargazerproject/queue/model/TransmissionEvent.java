package com.stargazerproject.queue.model;

import com.stargazerproject.information.model.Transmission;

/** 
 *  @name lmax disruptor 专用的EventModel
 *  @illustrate Transmission队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class TransmissionEvent {
	
	private Transmission transmission;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransmissionEvent() {}
	
    public void clear(){
    		transmission = null;
    	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

}
