package com.stargazerproject.log.collocation.resources;


import org.apache.log4j.Logger;

import com.stargazerproject.log.Log;

public class LocalLog implements Log{
	
	@Override
	public void DEBUG(Object object, String message) {
		Logger.getLogger(object.getClass()).debug(message);
	}

	@Override
	public void INFO(Object object, String message) {
		Logger.getLogger(object.getClass()).info(message);
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
