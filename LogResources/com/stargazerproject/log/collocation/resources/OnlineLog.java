package com.stargazerproject.log.collocation.resources;


import com.stargazerproject.log.Log;

public class OnlineLog implements Log{
	
	@Override
	public void DEBUG(Object object, String message) {
		System.out.println("OnlineLog Debug" + message);
	}

	@Override
	public void INFO(Object object, String message) {
		System.out.println("OnlineLog Info" + message);
	}

	@Override
	public void WARN(Object object, String message) {
		System.out.println("OnlineLog Warn" + message);
	}

	@Override
	public void ERROR(Object object, String message) {
		System.out.println("OnlineLog Error" + message);
	}

	@Override
	public void FATAL(Object object, String message) {
		System.out.println("OnlineLog Fatal" + message);
	}

}
