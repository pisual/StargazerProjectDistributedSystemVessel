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
import com.stargazerproject.characteristic.BaseCharacteristic;
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
	
	@Autowired
	@Qualifier("stargazerProjectParameterList")
	protected Object stargazerProjectParameterList;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	private BigCacheIndexCahceShell() {}
	
	@Override
	@Bean(name="bigCacheIndexCahceCharacteristicInitialize")
	@Lazy(true)
	public Optional<Cache<String, Map<String, Integer>>> characteristic() {
		return Optional.of(bigCacheIndexCahceCharacteristic);
	}
	
}