package com.stargazerproject.cache.aop.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.cache.impl.resources.OrderCacheLoadingCacheCharacteristic;
import com.stargazerproject.log.LogMethod;

/** 
 *  @name 针对被NeededInject标注的注解进行参数注入
 *  @illustrate 当BaseCharacteristic的characteristic方法被调用时，对NeededInject标注的注解进行参数注入
 *  @author Felixerio
 *  **/
@Component
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
public class ParametersInjectAOPConfiguration {
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> cache;
	
	/** @construction 初始化构造 **/
	private ParametersInjectAOPConfiguration() {}
	
	/** @illustrate orderCache 中的Set方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.characteristic.BaseCharacteristic.characteristic())")
	public void characteristicMethod(){}
	
	/** @illustrate orderCache 中的Set的AOP切点的具体方法**/
	@Around("characteristicMethod")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		try {
			System.out.println("AOP 注入");
			Object object = proceedingJoinPoint.getThis();
			Field[] field = object.getClass().getDeclaredFields();
			for(int i = 0 ; i < field.length; i++){  
		    	   if(field[i].isAnnotationPresent(NeededInject.class)){
		    		   field[i].setAccessible(true);
		    		   try {
		    				NeededInject neededInject = field[i].getAnnotation(NeededInject.class);
		    				switch (neededInject.type()) {
							case "SystemParametersCache":
								System.out.println("注入 " + cache.get(Optional.of(field[i].getName())).get());
								field[i].set(object, cache.get(Optional.of(field[i].getName())).get());
								break;

							default:
								break;
							}
		    				
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
		    		   }
	    	       }
			
			
			proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			baseLog.ERROR(proceedingJoinPoint.getThis(), throwable.getMessage());
			throw throwable;
		}
	}
	}
	