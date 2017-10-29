package com.stargazerproject.kernel.impl;

import com.stargazerproject.kernel.KernelControl;
import com.stargazerproject.validation.impl.ObjectValidation;

public class KernelControlImpl implements KernelControl {

	private ObjectValidation objectValidation;
	
	@Override
	public void start(String[] args) {
		cellsBootArgsChecks(args);
		cellsArgsInject(args);
	}
	
	private void cellsBootArgsChecks(String[] args){
		for(String arg : args){
			objectValidation.noNull(arg);
		}
	}
	
	private void cellsArgsInject(String[] args){
		for(String arg : args){
			argInjectHandle(arg);
		}
	}
	
	private void argInjectHandle(String ars){
		if(ars.indexOf(ars) >= 0){
			System.setProperty(ars, ars.split(":")[1]);
		}
		else{
			throw new NullPointerException(ars + " Not Configured");
		}
	}

}
