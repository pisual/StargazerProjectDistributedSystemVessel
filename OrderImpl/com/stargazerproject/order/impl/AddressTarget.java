package com.stargazerproject.order.impl;

import com.google.common.base.Optional;
import com.stargazerproject.order.Target;

@SuppressWarnings("unused")
public class AddressTarget implements Target{
	
	/** 指令IP **/
	private String ip;
	
	/** 指令端口 **/
	private Integer port;
	
	public AddressTarget(Optional<String> ipArg, Optional<Integer> portArg) {
		ip = ipArg.get();
		port = portArg.get();
	}
	
}
