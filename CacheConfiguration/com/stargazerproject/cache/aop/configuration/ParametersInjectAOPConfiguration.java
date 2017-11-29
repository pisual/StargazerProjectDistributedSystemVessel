package com.stargazerproject.cache.aop.configuration;

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
	
	/** @construction 初始化构造 **/
	private ParametersInjectAOPConfiguration() {}
	
	/** @illustrate orderCache 中的Set方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.characteristic.BaseCharacteristic.characteristic())")
	public void characteristicMethod(){}
	
	/** @illustrate orderCache 中的Set的AOP切点的具体方法**/
	@Around("characteristicMethod")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		try {
			Object object = proceedingJoinPoint.getThis();
			
			Field[] fs = object.getClass().getDeclaredFields();
			
		       for(int i = 0 ; i < fs.length; i++){  
		    	   Field f = fs[i];  
		       f.setAccessible(true); //设置些属性是可以访问的  
		       
		       f.getName();
		       
		       f.set(object, "");
		    	   
		       }
			
			proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			baseLog.ERROR(proceedingJoinPoint.getThis(), throwable.getMessage());
			throw throwable;
		}
	}
	
	
	
}