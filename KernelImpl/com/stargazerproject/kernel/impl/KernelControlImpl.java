package com.stargazerproject.kernel.impl;

import com.stargazerproject.kernel.KernelControl;
import com.stargazerproject.validation.ObjectCheck;
import com.stargazerproject.validation.impl.ObjectValidation;

public class KernelControlImpl implements KernelControl {

	private ObjectCheck objectValidation = new ObjectValidation();
	
	@Override
	public void start(String[] args) {
		cellsBootArgsChecks(args);
		cellsArgsInject(args);
	}
	
	@Override
	public void close() {
	}
	
	private void defaultArgsInject(){
		System.setProperty("Cells_Kind", "Cells_Master");
		System.setProperty("Cells_ServerCentre_Address", "127.0.0.1:2181");
		System.setProperty("java.awt.headless", "false");
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
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
			System.setProperty(ars.split(":")[0], ars.split(":")[1]);
		}
		else{
			throw new NullPointerException(ars + " Not Configured");
		}
	}

}
