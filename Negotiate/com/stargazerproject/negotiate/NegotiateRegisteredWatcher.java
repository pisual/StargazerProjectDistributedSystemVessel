package com.stargazerproject.negotiate;

import com.google.common.base.Optional;

public interface NegotiateRegisteredWatcher{

	/**
	 * @MethodName 注册循环触发的监听器
	 * @param InfiniteWatcher 监听器组合配置
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public <T> void registeredCirculationWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception;
	
	/**
	 * @MethodName 注册单次触发的监听器
	 * @param SingleWatcher 监听器组合配置
	 * @author Felixerio
	 * **/
	public <T> void registeredSingleWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch);
	
}
