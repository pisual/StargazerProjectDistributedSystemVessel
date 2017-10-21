package com.stargazerproject.sequence.aop.configuration;

import java.util.concurrent.TimeUnit;

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
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.sequence.SequenceMethod;

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
	
	/** @illustrate SequenceMethod(核心序列引导) 中的method方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.sequence.SequenceMethod.method())")
	public void method(){}
	
	/** @illustrate SequenceMethod(核心序列引导) 中的SequenceMethod的AOP切点的具体方法**/
	@Around("method()")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		try {
			SequenceMethod sequenceMethod = (SequenceMethod) proceedingJoinPoint.getTarget();
			if(sequenceMethod.getWaitMethod().get()){
				baseLog.INFO(this, "Wait Sequence Method " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
				Optional<String> waitPoint =  Optional.of(proceedingJoinPoint.getTarget().getClass().getSimpleName());
				initialization(waitPoint);
				proceedingJoinPoint.proceed();
				waitResult(waitPoint, getIntegerParameters("Sequence_Retries_Number"), getIntegerParameters("Sequence_Intervaltime_Secsond"), Optional.of(TimeUnit.SECONDS));
			}
			else{
				baseLog.INFO(this, "Sequence Method " + proceedingJoinPoint.getTarget().getClass().getSimpleName());
				proceedingJoinPoint.proceed();
			}
		} catch (Throwable throwable) {
			baseLog.ERROR(proceedingJoinPoint.getThis(), throwable.getMessage());
			throw throwable;
		}
	}
	
	private Boolean waitResult(Optional<String> waitPoint, Optional<Integer> retriesNumberArg, Optional<Integer> intervaltimeArg, Optional<TimeUnit> timeUnit){
		int retriesNumber = retriesNumberArg.get();
		while(true){
			if(systemParameter.get(waitPoint).get().equals("Continue")){
				baseLog.INFO(this, "waitPoint Has Pass : " + waitPoint.get());
				return Boolean.TRUE;
			}
			else{
				baseLog.DEBUG(this, "Wait " + waitPoint.get() + " Complete, Timeout Will Exit, Number Of Remaining Attempts : " + retriesNumber);
				wait(intervaltimeArg.get(), timeUnit.get());
				if( (--retriesNumber) < 0){
					baseLog.ERROR(this, "waitPoint Has Error : " + waitPoint.get());
					return Boolean.FALSE;
				}
			}
		}
	}
	
	private void wait(int time, TimeUnit timeUnit){
			try {
				timeUnit.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	private void initialization(Optional<String> waitPoint){
		systemParameter.put(waitPoint, Optional.of("Wait"));
		baseLog.INFO(this, "waitPoint has Creat : " + waitPoint.get());
	}
	
	private Optional<Integer> getIntegerParameters(String parameter){
		return Optional.of(Integer.parseInt(systemParameter.get(Optional.of(parameter)).get()));
	}
	
}