package com.stargazerproject.cache.impl.resources.shell;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;

/** 
 *  @name bigCacheIndexCahce 的Map初始化
 *  @illustrate 对SystemParameter所需要的特征Cache进行初始化
 *  @author Felixerio
 *  **/
@Component(value="bigCacheIndexCahceShell")
@Qualifier("bigCacheIndexCahceShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BigCacheIndexCahceShell implements BaseCharacteristic<Cache<String, Map<String, Integer>>>{

	@Autowired
	@Qualifier("bigCacheIndexCahceCharacteristic")
	protected Cache<String, Map<String, Integer>> bigCacheIndexCahceCharacteristic;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BigCacheIndexCahceShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BigCacheIndexCahceShell(Optional<Cache<String, Map<String, Integer>>> bigCacheIndexCahceCharacteristicArg){
		bigCacheIndexCahceCharacteristic = bigCacheIndexCahceCharacteristicArg.get();
	}
	
	@Override
	@Bean(name="bigCacheIndexCahceCharacteristic")
	@Lazy(true)
	public Optional<Cache<String, Map<String, Integer>>> characteristic() {
		return Optional.of(bigCacheIndexCahceCharacteristic);
	}
	
}