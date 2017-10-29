package com.stargazerproject.resources.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.inject.AnnotationClassSequenceScanner;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.service.baseinterface.Services;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceParameterList")
@Qualifier("serviceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceParameterList implements BaseCharacteristic<List<AbstractIdleService>>{

	@Autowired
	@Qualifier("annotationClassSequenceScannerImpl")
	private AnnotationClassSequenceScanner annotationClassSequenceScanner;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	/** @illustrate 内部服务列表**/
	private List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
	
	protected ServiceParameterList() {}
	
	private void serviceListInitialize(){
		try {
			annotationClassSequenceScanner.sequenceClassName(Optional.of("com.stargazerproject"), Optional.of(Services.class))
			                              .forEach(x -> serviceList.add((BeanContainer.instance().getBean(Optional.of(x), AbstractIdleService.class))));;
		} catch (ClassNotFoundException e) {
			baseLog.ERROR(this, e.getMessage());
		} catch (IOException e) {
			baseLog.ERROR(this, e.getMessage());
		}
	}

	@Override
	@Bean(name="serviceListCharacteristic")
	@Lazy(true)
	public Optional<List<AbstractIdleService>> characteristic() {
		serviceListInitialize();
		return Optional.of(serviceList);
	}
}
