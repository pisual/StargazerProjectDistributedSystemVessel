package com.stargazerproject.log.collocation.resources;


import com.stargazerproject.log.Log;

public class OnlineLog implements Log{
	
	@Override
	public void DEBUG(Object object, String message) {
	}

	@Override
	public void INFO(Object object, String message) {
		System.out.println("OnlineLog " + message);
	}

	@Override
	public void WARN(Object object, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ERROR(Object object, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FATAL(Object object, String message) {
		// TODO Auto-generated method stub
		
	}

}
