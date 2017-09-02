package com.stargazerproject.log.impl;

import com.stargazerproject.log.Log;
import com.stargazerproject.log.LogControl;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.log.resources.BasicLog;

public abstract class BaseLog implements LogMethod, LogControl{
	
	protected Log log;
	
	protected BaseLog() {
		log = new BasicLog();
	}

	@Override
	public void DEBUG(Object object, String message) {
		log.DEBUG(object, message);
	}

	@Override
	public void INFO(Object object, String message) {
		log.INFO(object, message);
	}

	@Override
	public void WARN(Object object, String message) {
		log.WARN(object, message);
	}

	@Override
	public void ERROR(Object object, String message) {
		log.ERROR(object, message);
	}

	@Override
	public void FATAL(Object object, String message) {
		log.FATAL(object, message);
	}
	
}
