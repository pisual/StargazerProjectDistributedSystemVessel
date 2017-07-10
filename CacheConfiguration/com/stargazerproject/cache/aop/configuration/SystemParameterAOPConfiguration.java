package com.stargazerproject.cache.aop.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.service.WorkInServiceState;

/** 
 *  @name SystemParameter的AOP
 *  @illustrate 针对于SystemParameter实现初始化过程的Shell注入及其他的操作进行AOP控制
 *  @author Felixerio
 *  **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
public class SystemParameterAOPConfiguration {
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	/** @illustrate 获取SystemParameterCache(系统参数缓存) ServerControl接口
	 *  @DeclareParents 这个接口为Spring后期织入
	 * **/
	@Autowired
	@Qualifier("systemParameterCacheServerListener")
	private WorkInServiceState systemParameterServerState;
	
	/** @construction 初始化构造 **/
	private SystemParameterAOPConfiguration() {}
	
	/** @illustrate SystemParameterCache(系统参数缓存) 中的Set方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.cache.Cache.put(com.google.common.base.Optional,com.google.common.base.Optional)) && args(key,value) && bean(systemParameterCahce)")
	public void setMethod(Optional<?> key, Optional<?> value){}
	
	/** @illustrate SystemParameterCache(系统参数缓存) 中的Set的AOP切点的具体方法**/
	@Around("setMethod(key, value)")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint, Optional<?> key, Optional<?> value) throws Throwable{
		try {
			systemParameterServerState.serverstateCheck();
			proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			baseLog.ERROR(proceedingJoinPoint.getClass(), throwable.getMessage());
			throw throwable;
		}
	}
	
	/** @illustrate SystemParameterCache(系统参数缓存) 中的Get方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.cache.Cache.get(com.google.common.base.Optional)) && args(key) && bean(systemParameterCahce)")
	public void getMethod(Optional<?> key){}
	
	/** @illustrate SystemParameterCache(系统参数缓存) 中的Get方法的AOP切点的具体方法**/
	@Around("getMethod(key)")
	public Object getMethodAround(ProceedingJoinPoint proceedingJoinPoint, Optional<?> key) throws Throwable{
		try{
			systemParameterServerState.serverstateCheck();
			return proceedingJoinPoint.proceed();
		}catch (Throwable throwable){
			baseLog.ERROR(proceedingJoinPoint.getClass(), throwable.getMessage());
			throw throwable;
		}
	}
	
}