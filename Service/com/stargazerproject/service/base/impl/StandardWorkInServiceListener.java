package com.stargazerproject.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.util.concurrent.Service.Listener;
import com.stargazerproject.log.LogMethod;

/** 
 *  @name 标准服务控制
 *  @illustrate 包含了基础的服务控制，通过调用serverStartserverStop或serverStop来改变服务状态
 *  @author Felixerio
 *  **/
public class StandardWorkInServiceListener extends Listener{
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod baseLog;
	
	/** @construction 初始化构造 **/
	public StandardWorkInServiceListener() {}
	
}