package com.stargazerproject.cache.impl.resources.shell;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.inject.AnnotationScanner;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.resources.Parameters;
import com.stargazerproject.spring.container.impl.BeanContainer;

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
	@Qualifier("annotationScannerImpl")
	private AnnotationScanner annotationScanner;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	private SystemParameterCahceShell() {}
	
	@Override
	@Bean(name="systemParameterCahceCharacteristicInitialize")
	@Lazy(true)
	public Optional<Cache<String, String>> characteristic() {
		getParamentListFromAnnotation();
		return Optional.of(systemParameterCahceCharacteristic);
	}
	
	/**
	 * @illustrate 获取指令Annotation的Class
	 * **/
	private void getParamentListFromAnnotation(){
		try {
			Multimap<Class<?>, Map.Entry<String, List<Object>>> scoreMultimap = annotationScanner.getClassAnnotationContent(Optional.of("com.stargazerproject"), Optional.of(Parameters.class));
			scoreMultimap.values().stream().filter(x -> x.getKey().equals("value"))
			                               .map(z -> z.getValue().get(0).toString())
			                               .collect(Collectors.toList())
					              .forEach(s -> getParamentListFromClass(BeanContainer.instance().getBean(Optional.of(s), Object.class)));
		} catch (ClassNotFoundException e) {
			baseLog.ERROR(this, e.getMessage());
		} catch (IOException e) {
			baseLog.ERROR(this, e.getMessage());
		}
	
	}
	
	/**
	 * @illustrate 从CLass文件获取参数并注入到Map列表
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