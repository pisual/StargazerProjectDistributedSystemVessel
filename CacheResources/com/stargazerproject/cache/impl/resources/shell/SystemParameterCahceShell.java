package com.stargazerproject.cache.impl.resources.shell;

import java.lang.reflect.Field;

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
 *  @name SystemParameter的Map初始化
 *  @illustrate 对SystemParameter所需要的特征Cache进行初始化
 *  @author Felixerio
 *  **/
@Component(value="systemParameterCahceShell")
@Qualifier("systemParameterCahceShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SystemParameterCahceShell implements BaseCharacteristic<Cache<String, String>>{

	@Autowired
	@Qualifier("systemParameterCahceCharacteristic")
	protected Cache<String, String> systemParameterCahceCharacteristic;
	
	@Autowired
	@Qualifier("cacheParameters")
	protected Object cacheParameters;
	
	@Autowired
	@Qualifier("queueParameters")
	protected Object queueParameters;
	
	@Autowired
	@Qualifier("uiParameters")
	protected Object uiParameters;
	
	@Autowired
	@Qualifier("negotiateParameters")
	protected Object negotiateParameters;
	
	@Autowired
	@Qualifier("systemParameters")
	protected Object systemParameters;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	private SystemParameterCahceShell() {}
	
	@Override
	@Bean(name="systemParameterCahceCharacteristicInitialize")
	@Lazy(true)
	public Optional<Cache<String, String>> characteristic() {
		getParamentListFromClass(cacheParameters);
		getParamentListFromClass(queueParameters);
		getParamentListFromClass(uiParameters);
		getParamentListFromClass(systemParameters);
		getParamentListFromClass(negotiateParameters);
		return Optional.of(systemParameterCahceCharacteristic);
	}
	
	/**
	 * @illustrate 从CLass文件获取参数并注入到Mao列表
	 * **/
	private void getParamentListFromClass(Object object){
		try {
			Field[] valueFields = object.getClass().getDeclaredFields();
			for(Field valueField : valueFields){
				valueField.setAccessible(true);
				systemParameterCahceCharacteristic.put(Optional.of(valueField.getName()), Optional.of(valueField.get(valueField.getName()).toString()));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			baseLog.ERROR(this, e.getLocalizedMessage());
		}
	}
}