package com.stargazerproject.log.collocation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.log.impl.BaseLog;

/** 
 *  @name 开发级别日志记录模式
 *  @illustrate 以开发级别进行日志的处理，全量处理
 *  @author Felixerio
 *  **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("logRecord")
public class LogRecord extends BaseLog implements StanderCharacteristicShell<Log>{

	@Override
	public void initialize(Optional<Log> logArg) {
		log = logArg.get();
	}
	
}