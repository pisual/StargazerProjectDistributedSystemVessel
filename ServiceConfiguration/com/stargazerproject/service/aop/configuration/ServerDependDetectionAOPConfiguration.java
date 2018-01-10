package com.stargazerproject.service.aop.configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	private void initializationServerSequenceMap(){
		List<String> serverList = serviceParameterList.characteristic().get();
		for(int i=1; i<=serverList.size(); i++){
			String servername = serverList.get(i-1).replace("Manage", "");
			servername = firstChartoLowerCase(servername);
			serverSequenceMap.put(i, servername);
		}
	}
	
	/** @illustrate StanderServiceShell 中的Set方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.service.baseinterface.StanderServiceShell.startUp())")
	public void startUpMethod(){}
	
	/** @illustrate StanderServiceShell 中的Set的AOP切点的具体方法**/
	@Around("startUpMethod()")
	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String servername = proceedingJoinPoint.getTarget().getClass().getSimpleName();
		servername = firstChartoLowerCase(servername);
		
		while(dependOnDelay(Optional.of(servername)).get() == Boolean.FALSE){
			TimeUnit.MICROSECONDS.sleep(100);
		}
		
		proceedingJoinPoint.proceed();
		cache.put(Optional.of(servername), Optional.of(Boolean.TRUE));
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
	
	private String firstChartoLowerCase(String servername){
		return servername.substring(0, 1).toLowerCase() + servername.substring(1, servername.length());
	}

}
	