package com.stargazerproject.order.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.order.Target;


/** 指令传输 **/
public final class Transmission{
	/** 指令发出地址 **/
	private Target source;
	/** 指令接收地址 **/
	private Target receive;

	public Transmission(Optional<Target> sourceArg, Optional<Target> receiveArg) {
		source = sourceArg.get();
		receive = receiveArg.get();
	}
	
	@Override
	public String toString() {
	    return MoreObjects.toStringHelper(this)
	            .add("sourceTarget", source)
	            .add("receiveTarget", receive).toString();
	}

}
