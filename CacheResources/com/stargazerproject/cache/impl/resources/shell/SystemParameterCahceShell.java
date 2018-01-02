package com.stargazerproject.cache.impl.resources.shell;

import java.io.IOException;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.inject.AnnotationClassSequenceScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
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
	@Qualifier("annotationClassSequenceScannerImpl")
	private AnnotationClassSequenceScanner annotationClassSequenceScanner;
	
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
	private SystemParameterCahceShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SystemParameterCahceShell(Optional<LogMethod> baseLogArg,
			                         Optional<Cache<String, String>> systemParameterCahceCharacteristicArg,
			                         Optional<AnnotationClassSequenceScanner> annotationClassSequenceScannerArg) {
		baseLog = baseLogArg.get();
		annotationClassSequenceScanner = annotationClassSequenceScannerArg.get();
		systemParameterCahceCharacteristic = systemParameterCahceCharacteristicArg.get();
	}
	
	@Override
	public Optional<Cache<String, String>> characteristic() {
		getParamentListFromAnnotation();
		return Optional.of(systemParameterCahceCharacteristic);
	}
	
	/**
	 * @illustrate 获取指令Annotation的Class
	 * **/
	private void getParamentListFromAnnotation(){
		try {
			annotationClassSequenceScanner.sequenceClassName(Optional.of("com.stargazerproject"), Optional.of(Parameters.class))
			                              .forEach(x -> getParamentListFromClass((BeanContainer.instance().getBean(Optional.of(x), Object.class))));;
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