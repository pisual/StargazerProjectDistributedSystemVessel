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
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.util.SequenceUtil;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="initializationCellsGroupModel")
@Qualifier("initializationCellsGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitializationCellsGroupModel implements CellsTransaction<String, String>{
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public InitializationCellsGroupModel() { 
		super(); 
		}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "initializationCellsGroupModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="initializationCellsGroupModel", 
	                threadPoolKey = "initializationCellsGroupModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public boolean method(Optional<Cache<String, String>> cache) {
		cache.get().put(Optional.of("OrderID"), Optional.of(SequenceUtil.getUUID()));
		log.INFO(this, "This_Cells_UUID Initialization: " + cache.get().get(Optional.of("OrderID")).get());
		return Boolean.TRUE;
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
	
}
