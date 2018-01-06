package com.stargazerproject.log.resources.shall;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.Log;

@Component(value="localLogShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("localLogShell")
public class LocalLogShell implements Log, BaseCharacteristic<Log>{
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LocalLogShell() {}
	
	@Override
	public Optional<Log> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void DEBUG(Object object, String message) {
		Logger.getLogger(object.getClass()).debug(message);
	}

	@Override
	public void INFO(Object object, String message) {
		Logger.getLogger(object.getClass()).info(message);
	}

	@Override
	public void WARN(Object object, String message) {
		Logger.getLogger(object.getClass()).warn(message);
	}

	@Override
	public void ERROR(Object object, String message) {
		Logger.getLogger(object.getClass()).error(message);
	}

	@Override
	public void FATAL(Object object, String message) {
		Logger.getLogger(object.getClass()).fatal(message);
	}

}
