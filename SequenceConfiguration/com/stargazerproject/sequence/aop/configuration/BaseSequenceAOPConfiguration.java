package com.stargazerproject.sequence.aop.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.cache.Cache;
import com.stargazerproject.log.LogMethod;

/** 
 *  @name BaseKernelModel的AOP
 *  @illustrate 针对于BaseKernelModel实现初始化过程的Shell注入及其他的操作进行AOP控制
 *  @author Felixerio
 *  **/
@Component
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
public class BaseSequenceAOPConfiguration {
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	/** @construction 初始化构造 **/
	private BaseSequenceAOPConfiguration() {}
	
}