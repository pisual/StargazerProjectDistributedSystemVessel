package com.stargazerproject.zookeeper.server;

import java.io.IOException;


public interface ServerZookeeperLeaderMethod {
	
	/**
	 * @MethodName 选举Leader
	 * @author Felixerio
	 * @throws Exception 
	 * **/
	public void startSelectLeader() throws Exception;
	
	/**
	 * @MethodName 选举Leader
	 * @author Felixerio
	 * @throws Exception 
	 * **/
	public void startSelectLeaderAwait() throws Exception;
	
	/**
	 * @MethodName 放弃Leader
	 * @author Felixerio
	 * @throws IOException 
	 * **/
	public void giveUpLeader() throws IOException;
	
	public String getSelectLeader() throws Exception;
}
