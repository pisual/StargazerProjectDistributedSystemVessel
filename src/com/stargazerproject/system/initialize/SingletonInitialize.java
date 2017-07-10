package com.stargazerproject.system.initialize;

import com.stargazerproject.cache.impl.impl.OrderCache;
import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.ui.assembly.impl.ConsoleTextPane;
import com.stargazerproject.ui.assembly.impl.RightConsoleTextPane;

public class SingletonInitialize {
	private static SingletonInitialize singletonInitialize;

	public static final SingletonInitialize getInstance() {
		if (singletonInitialize == null) {
			singletonInitialize = new SingletonInitialize();
		}
		return singletonInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(SingletonInitialize.class,"Singleton SingletonInitialize Initialize");
		//ConsoleTextPane.getInstance();
		//RightConsoleTextPane.getInstance();
		//OrderCache.getInstance();
	}

	private SingletonInitialize() {
	}
}
