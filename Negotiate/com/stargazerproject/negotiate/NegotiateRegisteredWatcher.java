package com.stargazerproject.negotiate;

import com.google.common.base.Optional;

public interface NegotiateRegisteredWatcher{

	/**
	 * @MethodName 注册监听器
	 * @param Optional<String> nodeName
	 *        Optional<String> nodePath
	 *        Optional<T> watch
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public <T> void registeredWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<String> watchName, Optional<T> watch) throws Exception;
	
	/**
	 * @MethodName 删除监听器
	 * @param Optional<String> watchName
	 * @author Felixerio
	 * **/
	public <T> void removeWatcher(Optional<String> watchName) throws Exception;
	
}
