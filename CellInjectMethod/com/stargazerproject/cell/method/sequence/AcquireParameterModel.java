package com.stargazerproject.cell.method.sequence;

import org.apache.curator.framework.api.CuratorListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="acquireParameterModel")
@Qualifier("acquireParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AcquireParameterModel implements CellsTransaction<String, String>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public AcquireParameterModel() {super();}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "acquireParameterModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="acquireParameterModel", 
	                threadPoolKey = "acquireParameterModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public boolean method(Optional<Cache<String, String>> cache){
	try {
		Optional<CuratorListener> negotiateNodeCuratorListenerCharacteristic = BeanContainer.instance().getBean(Optional.of("negotiateInjectParameterTreeCacheListenerCharacteristic"), Optional.class);
		nodeNegotiate.registeredSingleWatcher(systemParameter.get(Optional.of("This_Cells_UUID")), Optional.of("/System/EdenCells/"), negotiateNodeCuratorListenerCharacteristic);
		nodeNegotiate.creatEphemeralNode(systemParameter.get(Optional.of("This_Cells_UUID")), Optional.of("/System/EdenCells/"), Optional.absent());
		log.INFO(this, "acquireParameterModel Complete: " + systemParameter.get(Optional.of("This_Cells_UUID")).get());
		return Boolean.TRUE;
	} catch (Exception e) {
		log.ERROR(this, e.getMessage());
	}
		return Boolean.FALSE;
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
