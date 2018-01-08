package com.stargazerproject.resources.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.inject.AnnotationClassSequenceScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.service.baseinterface.Services;

@Component(value="serviceParameterList")
@Qualifier("serviceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceParameterList implements BaseCharacteristic<List<String>>{

	@Autowired
	@Qualifier("annotationClassSequenceScannerImpl")
	private AnnotationClassSequenceScanner annotationClassSequenceScanner;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	/** @illustrate 内部服务列表**/
	private List<String> serviceList;
	
	protected ServiceParameterList() {}
	
	private void serviceListInitialize(){
		try {
			serviceList = annotationClassSequenceScanner.sequenceClassName(Optional.of("com.stargazerproject"), Optional.of(Services.class));
		} catch (ClassNotFoundException e) {
			baseLog.ERROR(this, e.getMessage());
		} catch (IOException e) {
			baseLog.ERROR(this, e.getMessage());
		}
	}

	@Override
	public Optional<List<String>> characteristic() {
		serviceListInitialize();
		return Optional.of(serviceList);
	}
}
