package com.stargazerproject.service;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;

/** 
 *  @name Server 资源
 *  @illustrate Server 初始化服务列表
 *  @author Felixerio
 *  **/
public interface ServiceResources {
	public Optional<List<AbstractIdleService>> allServiceList();
	public void initializationServiceList(Optional<List<String>> serviceList);
}
