package com.stargazerproject.cell.method.sequence;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.util.SequenceUtil;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="parameterInitializationModel")
@Qualifier("parameterInitializationModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ParameterInitializationModel implements CellsTransaction<String, String>{
	
	/** @name 新生区路径 **/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_EdenNodePath;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("negotiateParametersInjectInitializationListenerCharacteristic")
	private BaseCharacteristic<TreeCacheListener> negotiateParametersInjectInitializationListener;
	
	public ParameterInitializationModel() { 
		super(); 
		}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "parameterInitializationModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="parameterInitializationModel", 
	                threadPoolKey = "parameterInitializationModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public boolean method(Optional<Cache<String, String>> cache) {
		try {
			nodeNegotiate.registeredWatcher(Optional.of(""), Optional.of(Kernel_Negotiate_BasePath_EdenNodePath), Optional.of("parameterInitializationModel"), negotiateParametersInjectInitializationListener.characteristic());
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
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
			log.WARN(this, "Event FallBack : TimeOut");
		}
		else{
			log.WARN(this, "Event FallBack : " + throwable.getMessage());
		}
		return Boolean.FALSE;
    }
	
}
