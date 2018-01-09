package com.stargazerproject.service.aop.configuration;

import java.util.List;
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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.service.Server;
import com.stargazerproject.service.ServerDepend;
import com.stargazerproject.service.baseinterface.WorkInServiceState;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name 针对被NeededInject标注的注解进行参数注入
 *  @illustrate 当BaseCharacteristic的characteristic方法被调用时，对NeededInject标注的注解进行参数注入
 *  @author Felixerio
 *  **/
@Component(value="serverDependDetectionAOPConfiguration")
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
@Qualifier("serverDependDetectionAOPConfiguration")
public class ServerDependDetectionAOPConfiguration {
	
	@Autowired
	@Qualifier("kernelService")
	private Server server;
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	@Autowired
	@Qualifier("serverCache")
	private Cache<String,Boolean> cache;
	
	@Autowired
	@Qualifier("serviceParameterList")
	private BaseCharacteristic<List<String>> serviceParameterList;
	
	@Autowired
	@Qualifier("serverDependCharacteristic")
	private BaseCharacteristic<ServerDepend> serverDependCharacteristic;
	
	/** @construction 双向Map
	 * Key（Value）Integer：服务序号
	 * Value（Key）String ：服务名称
	 *  **/
	private static BiMap<Integer,String> serverSequenceMap = HashBiMap.create();
	
	/** @construction 初始化构造 **/
	private ServerDependDetectionAOPConfiguration() {}
	
	public void initializationServerSequenceMap(){
		List<String> serverList = serviceParameterList.characteristic().get();
		for(int i=1; i<=serverList.size(); i++){
			String servername = serverList.get(i-1).replace("Manage", "");
			servername = servername.substring(0, 1).toLowerCase() + servername.substring(1, servername.length());
			serverSequenceMap.put(i, servername);
		}
	}
	
	/** @illustrate StanderServiceShell 中的Set方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.service.baseinterface.StanderServiceShell.startUp())")
	public void startUpMethod(){}
	
	/** @illustrate StanderServiceShell 中的Set的AOP切点的具体方法**/
	@Around("startUpMethod()")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String serverName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
		serverName = serverName.substring(0, 1).toLowerCase() + serverName.substring(1, serverName.length());
		while(dependOnDelay(Optional.of(serverName)).get() == Boolean.FALSE){
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		
		proceedingJoinPoint.proceed();
		cache.put(Optional.of(serverName), Optional.of(Boolean.TRUE));
		}
	
	private Optional<Boolean> dependOnDelay(Optional<String> serverName){
		Integer index = serverSequenceMap.inverse().get(serverName.get());
		if(index == 1){
			return Optional.of(Boolean.TRUE);
		}
		else{
			int beforeIndex = index -1;
			String beforeServer = serverSequenceMap.get(beforeIndex);
			return cache.get(Optional.of(beforeServer));
		}
	}

}
	