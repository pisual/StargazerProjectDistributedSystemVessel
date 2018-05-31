package com.stargazerproject.cell.method.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;

/** 
 *  @name 创建基础节点
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="createBaseNodeModel")
@Qualifier("createBaseNodeModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateBaseNodeModel implements CellsTransaction<String, String>{
	
	/** @name 根路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_RootPath;
	
	/** @name 新生区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_EdenNodePath;
	
	/** @name 建组区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_ZoneNodePath;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	public CreateBaseNodeModel() { 
		super(); 
		}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "createBaseNodeModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="createBaseNodeModel", 
	                threadPoolKey = "createBaseNodeModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public boolean method(Optional<Cache<String, String>> cache) {
		try {
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_RootPath));
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_EdenNodePath));
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_ZoneNodePath));
			log.INFO(this, "CreateBaseNodeModel Complete: " + cache.get().get(Optional.of("OrderID")).get());
			return Boolean.TRUE;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return Boolean.FALSE;
		}
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* @param Throwable throwable
	* **/
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		if(null == throwable){
			log.WARN(this, "BaseEvent FallBack : TimeOut");
		}
		else{
			log.WARN(this, "BaseEvent FallBack : " + throwable.getMessage());
		}
		return Boolean.FALSE;
    }
	
	private void creatPersistentNode(Optional<String> path) throws Exception{
		if(nodeNegotiate.checkNodeExists(Optional.of(""), path) != Boolean.TRUE){
			nodeNegotiate.creatPersistentNode(Optional.of(""), path, Optional.absent());
			log.INFO(this, "Create Ephemeral Node : " + path.get());
		}
		else{
			log.WARN(this, "Create Ephemeral Node Error, The Node Already Exists : " + path.get());
		}
	}
	
}
