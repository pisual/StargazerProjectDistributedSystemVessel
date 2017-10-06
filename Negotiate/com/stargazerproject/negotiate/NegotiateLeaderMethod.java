package com.stargazerproject.negotiate;

import java.io.IOException;

import com.google.common.base.Optional;

public interface NegotiateLeaderMethod {
	
	/**
	 * @MethodName 选举Leader
	 * @author Felixerio
	 * @throws Exception 
	 * **/
	public <T> void startSelectLeader(Optional<String> nodeName, Optional<String> nodePath, Optional<T> listener) throws Exception;
	
	/**
	 * @MethodName 放弃Leader
	 * @author Felixerio
	 * @throws IOException 
	 * **/
	public void giveUpLeader(Optional<String> nodeName, Optional<String> nodePath) throws IOException;
	
	/**
	 * @MethodName 获取当前Leader
	 * @author Felixerio
	 * @throws IOException 
	 * **/
	public String getSelectLeader(Optional<String> nodeName, Optional<String> nodePath) throws Exception;
	
}
