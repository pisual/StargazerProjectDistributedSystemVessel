package com.stargazerproject.server;

import java.util.concurrent.Executor;

public enum EvenLoopExecutor implements Executor{
	 INSTANCE;
	@Override
	public void execute(Runnable command) {
	         new Thread(command).start();
	}
	 
}
