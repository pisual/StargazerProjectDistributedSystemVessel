package com.stargazerproject.negotiate.aop.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.log.LogMethod;

/** 
 *  @name BigCacheIndexCache的AOP
 *  @illustrate 针对于SystemParameter实现初始化过程的Shell注入及其他的操作进行AOP控制
 *  @author Felixerio
 *  **/
@Component(value="nodenNegotiateAOPConfiguration")
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
public class NodenNegotiateAOPConfiguration {
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	
	/** @construction 初始化构造 **/
	private NodenNegotiateAOPConfiguration() {}
	
}