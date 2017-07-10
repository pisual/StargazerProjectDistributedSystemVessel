package com.stargazerproject.model.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.model.order.Target;


/** 指令传输 **/
@SuppressWarnings("unused")
public final class Transmission{
	/** 指令发出地址 **/
	private Target source;
	/** 指令接收地址 **/
	private Target receive;

	public Transmission(Optional<Target> sourceArg, Optional<Target> receiveArg) {
		source = sourceArg.get();
		receive = receiveArg.get();
	}
	
}
