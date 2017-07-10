package com.stargazerproject.cache.resources.permanentcache;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;

/** 
 *  @name SystemParameter的Map初始化
 *  @illustrate 对SystemParameter所需要的特征Map进行初始化
 *  @author Felixerio
 *  **/
@Component
@Qualifier("systemParameterMap")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PermanentcacheSystemParameterMap implements BaseCharacteristic<Map<String, String>>{
	
	/** @illustrate SystemParameterCache(系统参数缓存)需要的特征(Map<String, String>)接口 **/
	private Map<String, String> map = new ConcurrentSkipListMap<String, String>();

	@Autowired
	@Qualifier("stargazerProjectParameterList")
	protected Object stargazerProjectParameterList;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	private PermanentcacheSystemParameterMap() {}
	
	@Override
	@Bean(name="systemParameterMapCharacteristic")
	@Lazy(true)
	public Optional<Map<String, String>> characteristic() {
		getParamentListFromClass();
		return Optional.of(map);
	}
	
	/**
	 * @illustrate 从CLass文件获取参数并注入到Mao列表
	 * **/
	private void getParamentListFromClass(){
		try {
			Field[] valueFields = stargazerProjectParameterList.getClass().getDeclaredFields();
			for(Field valueField : valueFields){
				valueField.setAccessible(true);
				map.put(valueField.getName(), valueField.get(valueField.getName()).toString());
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			baseLog.ERROR(this, e.getLocalizedMessage());
		}
	}
}