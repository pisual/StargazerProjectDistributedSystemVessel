package com.stargazerproject.log.resources;


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
		Logger.getLogger(object.getClass()).warn(message);
	}

	@Override
	public void ERROR(Object object, String message) {
		Logger.getLogger(object.getClass()).error(message);
	}

	@Override
	public void FATAL(Object object, String message) {
		Logger.getLogger(object.getClass()).fatal(message);
	}

}
