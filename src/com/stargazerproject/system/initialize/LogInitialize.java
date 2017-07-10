package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;


public class LogInitialize {
	private static LogInitialize logInitialize;

	public static final LogInitialize getInstance() {
		if (logInitialize == null) {
			logInitialize = new LogInitialize();
		}
		return logInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(LogInitialize.class, "LogInitialize Initialize");
	}

	private LogInitialize() {
	}
}
