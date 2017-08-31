package com.stargazerproject.log.collocation.resources;


import com.stargazerproject.log.Log;

public class BasicLog implements Log{
	
	@Override
	public void DEBUG(Object object, String message) {
		System.out.println("BasicLog DEBUG :" + object.toString() + " : " + message);
	}

	@Override
	public void INFO(Object object, String message) {
		System.out.println("BasicLog INFO :" + object.toString() + " : " + message);
	}

	@Override
	public void WARN(Object object, String message) {
		System.out.println("BasicLog WARN :" + object.toString() + " : " + message);
	}

	@Override
	public void ERROR(Object object, String message) {
		System.err.println("BasicLog ERROR :" + object.toString() + " : " + message);
	}

	@Override
	public void FATAL(Object object, String message) {
		System.out.println("BasicLog FATAL :" + object.toString() + " : " + message);
	}

}
